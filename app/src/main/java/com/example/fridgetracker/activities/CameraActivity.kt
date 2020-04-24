package com.example.fridgetracker.activities

//try 5
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fridgetracker.R
import com.example.fridgetracker.adapters.CameraAdapter
import com.example.fridgetracker.data.ReceiptImage
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.camera_test.*
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime


class CameraActivity : AppCompatActivity() {
    val REQUEST_CAMERA_PERMISSIONS = 1;
    val IMAGE_CAPTURE_CODE = 2;
    private lateinit var auth: FirebaseAuth
    lateinit var storage: FirebaseStorage
    lateinit var storageRef : StorageReference

    lateinit var database: FirebaseFirestore
    private var urls : ArrayList<String> = arrayListOf()
    lateinit var query: Query
    lateinit var adapter: CameraAdapter


    private val TAG = "CameraActivity"

//    val storageRef = storage.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //storage
        storage = Firebase.storage
        storageRef = storage.reference

        //database
        database = FirebaseFirestore.getInstance()

        //firebase auth
        auth = FirebaseAuth.getInstance()

        setContentView(R.layout.camera_test)

        btnTakePicture.setOnClickListener {

            var permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

            if(permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA),REQUEST_CAMERA_PERMISSIONS)
            } else {
                val cIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cIntent, IMAGE_CAPTURE_CODE)
            }
        }

        query = database.collection("users").document(auth.currentUser!!.email.toString()).collection("receipts")
        // initialize urls
        query.get().addOnSuccessListener { result ->
            for (document in result) {
                val receipt = document.toObject(ReceiptImage::class.java)
                urls.add(receipt!!.url)
            }
            println("inside urls initialization: " + urls)
            adapter.notifyDataSetChanged()

        }

        // regular adapter
        adapter = CameraAdapter(urls)
        cameraRecyclerView.layoutManager = GridLayoutManager(this,4)
        cameraRecyclerView.adapter = adapter
        cameraRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //actually menu button
        btnGallery.setOnClickListener {
            var main = Intent(this, MenuActivity::class.java)
            //main.putExtra("id", 1)
            startActivity(main)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_CAPTURE_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val bitmap = data!!.extras!!["data"] as Bitmap

                val mountainsRef = storageRef.child(auth.currentUser!!.email.toString() + LocalDateTime.now().toString() + ".jpg")

                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                var uploadTask = mountainsRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    // Handle unsuccessful uploads
                }.addOnSuccessListener {

                    val urlTask = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        println("ref.downloadUrl: " + mountainsRef.downloadUrl.toString())
                        mountainsRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            val url = downloadUri.toString()
                            // save downloadUri into firestore
                            urls.add(url)
                            println("urls: " + urls)
                            adapter.notifyDataSetChanged()
                            val image = hashMapOf(
                                "url" to url
                            )
                            var dbRef = database.collection("users").document(auth.currentUser!!.email.toString()).collection("receipts")
                            dbRef
//                                .set(user)
                                .add(image)
                                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

                            println("downloadUri: " + downloadUri) // downloadUri is https:// url, which can be entered into chrome and shows the pic
                        } else {
                            // Handle failures
                            println("urltask failure")
                        }
                    }
                    // wah get url end
                }


            }
        }
    }
}


