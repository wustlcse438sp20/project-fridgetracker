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
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fridgetracker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.face.FirebaseVisionFace
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*
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
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_CAPTURE_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                val bitmap = data!!.extras!!["data"] as Bitmap

                val mountainsRef = storageRef.child(auth.currentUser!!.email.toString() + LocalDateTime.now().toString() + ".jpg")


                val user = hashMapOf(
                    "image" to auth.currentUser!!.email.toString() + LocalDateTime.now().toString() + ".jpg"
                )
                database.collection("users").document(auth.currentUser!!.email.toString())
                    .set(user)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                var uploadTask = mountainsRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    // Handle unsuccessful uploads
                }.addOnSuccessListener {
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    // ...
                }

//                capturedImage.setImageBitmap(bitmap)
//
//                val fireImage = FirebaseVisionImage.fromBitmap(bitmap)
//
//                val options = FirebaseVisionFaceDetectorOptions.Builder()
//                    .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
//                    .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
//                    .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
//                    .build()
//
//                val detector = FirebaseVision.getInstance().getVisionFaceDetector(options)
//
//                val result = detector.detectInImage(fireImage)
//                    .addOnSuccessListener { faces ->
//                        for(face in faces) {
//                            if(face.smilingProbability != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
//                                val prob = face.smilingProbability
//                                if(prob > .80) {
//                                    smileText.text = "Smiling!"
//                                } else {
//                                    smileText.text = "=("
//                                }
//                            }
//                        }
//                    }
            }
        }
    }
}


//import com.example.fridgetracker.R
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.opensooq.supernova.gligar.GligarPicker
//import kotlinx.android.synthetic.main.activity_main.*
//
//class CameraActivity : AppCompatActivity() {
//
//    val PICKER_REQUEST_CODE = 30
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.camera_test)
//        GligarPicker().limit(10).disableCamera(false).cameraDirect(false).requestCode(PICKER_REQUEST_CODE)
//            .withActivity(this).show()
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode != Activity.RESULT_OK) {
//            return
//        }
//
//        when (requestCode) {
//            PICKER_REQUEST_CODE -> {
//                val imagesList = data?.extras?.getStringArray(GligarPicker.IMAGES_RESULT)
//                if (!imagesList.isNullOrEmpty()) {
//                    //imagesCount.text = "Number of selected Images: ${imagesList.size}"
//                }
//            }
//        }
//    }
//
//}


//import android.annotation.TargetApi
//import android.app.Activity
//import android.content.ContentUris
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.BitmapFactory
//import android.net.Uri
//
//import android.os.Build
//import android.os.Bundle
//import android.provider.DocumentsContract
//import android.provider.MediaStore
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.content.FileProvider
//import com.example.fridgetracker.R
//import java.io.File
//
//class CameraActivity : AppCompatActivity() {
//    //Our variables
//    private var mImageView: ImageView? = null
//    private var mUri: Uri? = null
//
//    //Our widgets
//    private lateinit var btnTakePicture: Button
//    private lateinit var btnGallery: Button
//
//    //Our constants
//    private val OPERATION_CAPTURE_PHOTO = 1
//    private val OPERATION_CHOOSE_PHOTO = 2
//
//    private fun initializeWidgets() {
//        btnTakePicture = findViewById(R.id.btnTakePicture)
//        btnGallery = findViewById(R.id.btnGallery)
//        mImageView = findViewById(R.id.capturedImage)
//    }
//
//    private fun show(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//
//    private fun capturePhoto() {
//        val capturedImage = File(externalCacheDir, "My_Captured_Photo.jpg")
//        if (capturedImage.exists()) {
//            capturedImage.delete()
//        }
//        capturedImage.createNewFile()
//        mUri = if (Build.VERSION.SDK_INT >= 24) {
//            FileProvider.getUriForFile(
//                this, "com.example.fridgetracker.fileprovider",
//                capturedImage
//            )
//        } else {
//            Uri.fromFile(capturedImage)
//        }
//
//        val intent = Intent("android.media.action.IMAGE_CAPTURE")
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
//        startActivityForResult(intent, OPERATION_CAPTURE_PHOTO)
//    }
//
//    private fun openGallery() {
//        val intent = Intent("android.intent.action.GET_CONTENT")
//        intent.type = "image/*"
//        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
//    }
//
//    private fun renderImage(imagePath: String?) {
//        if (imagePath != null) {
//            val bitmap = BitmapFactory.decodeFile(imagePath)
//            mImageView?.setImageBitmap(bitmap)
//        } else {
//            show("ImagePath is null")
//        }
//    }
//
//    private fun getImagePath(uri: Uri?, selection: String?): String {
//        var path: String? = null
//        val cursor = contentResolver.query(uri!!, null, selection, null, null)
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
//            }
//            cursor.close()
//        }
//        return path!!
//    }
//
//    @TargetApi(19)
//    private fun handleImageOnKitkat(data: Intent?) {
//        var imagePath: String? = null
//        val uri = data!!.data
//        //DocumentsContract defines the contract between a documents provider and the platform.
//        if (DocumentsContract.isDocumentUri(this, uri)) {
//            val docId = DocumentsContract.getDocumentId(uri)
//            if ("com.android.providers.media.documents" == uri!!.authority) {
//                val id = docId.split(":")[1]
//                val selsetion = MediaStore.Images.Media._ID + "=" + id
//                imagePath = getImagePath(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    selsetion
//                )
//            } else if ("com.android.providers.downloads.documents" == uri.authority) {
//                val contentUri = ContentUris.withAppendedId(
//                    Uri.parse(
//                        "content://downloads/public_downloads"
//                    ), java.lang.Long.valueOf(docId)
//                )
//                imagePath = getImagePath(contentUri, null)
//            }
//        } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
//            imagePath = getImagePath(uri, null)
//        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
//            imagePath = uri.path
//        }
//        renderImage(imagePath)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<out String>
//        , grantedResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantedResults)
//        when (requestCode) {
//            1 ->
//                if (grantedResults.isNotEmpty() && grantedResults.get(0) ==
//                    PackageManager.PERMISSION_GRANTED
//                ) {
//                    openGallery()
//                } else {
//                    show("Unfortunately You are Denied Permission to Perform this Operataion.")
//                }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            OPERATION_CAPTURE_PHOTO ->
//                if (resultCode == Activity.RESULT_OK) {
//                    val bitmap = BitmapFactory.decodeStream(
//                        getContentResolver().openInputStream(mUri!!)
//                    )
//                    mImageView!!.setImageBitmap(bitmap)
//                }
//            OPERATION_CHOOSE_PHOTO ->
//                if (resultCode == Activity.RESULT_OK) {
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        handleImageOnKitkat(data)
//                    }
//                }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.camera_test)
//
//        initializeWidgets()
//
//        btnTakePicture.setOnClickListener { capturePhoto() }
//        btnGallery.setOnClickListener {
//            //check permission at runtime
//            val checkSelfPermission = ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
//            )
//            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
//                //Requests permissions to be granted to this application at runtime
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
//                )
//            } else {
//                openGallery()
//            }
//        }
//    }
//}
//

//
//import android.annotation.TargetApi
//import android.app.Activity
//import android.app.ProgressDialog.show
//import android.content.ContentUris
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.os.Environment
//import android.provider.DocumentsContract
//import android.provider.MediaStore
//import android.view.View
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.content.FileProvider
//import com.example.fridgetracker.R
//import com.google.firebase.ml.vision.FirebaseVision
//import com.google.firebase.ml.vision.common.FirebaseVisionImage
//import com.google.firebase.ml.vision.face.FirebaseVisionFace
//import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.camera_test.*
//import java.io.File
//import java.io.IOException
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class CameraActivity : AppCompatActivity() {
//    val REQUEST_CAMERA_PERMISSIONS = 1
//    val IMAGE_CAPTURE_CODE = 2
//    val REQUEST_TAKE_PHOTO = 3
//
//    lateinit var currentPhotoPath: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.camera_test)
//
//        btnTakePicture.setOnClickListener {
//
//            var permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//
//            if(permission != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(android.Manifest.permission.CAMERA),REQUEST_CAMERA_PERMISSIONS)
//            } else {
//                val cIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivityForResult(cIntent, IMAGE_CAPTURE_CODE)
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == IMAGE_CAPTURE_CODE) {
//            if(resultCode == Activity.RESULT_OK) {
//                val bitmap = data!!.extras!!["data"] as Bitmap
//                capturedImage.setImageBitmap(bitmap)
//
//            }
//        }
//    }
//
//    @Throws(IOException::class)
//    private fun createImageFile(): File {
//        // Create an image file name
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//            "JPEG_${timeStamp}_", /* prefix */
//            ".jpg", /* suffix */
//            storageDir /* directory */
//        ).apply {
//            // Save a file: path for use with ACTION_VIEW intents
//            currentPhotoPath = absolutePath
//        }
//    }
//
//    private fun dispatchTakePictureIntent() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            // Ensure that there's a camera activity to handle the intent
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                // Create the File where the photo should go
//                val photoFile: File? = try {
//                    createImageFile()
//                } catch (ex: IOException) {
//                    // Error occurred while creating the File
//                    null
//                }
//                // Continue only if the File was successfully created
//                photoFile?.also {
//                    val photoURI: Uri = FileProvider.getUriForFile(
//                        this,
//                        "com.example.android.fileprovider",
//                        it
//                    )
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
//                }
//            }
//        }
//    }
//}
//class CameraActivity : AppCompatActivity() {
//    val REQUEST_CAMERA_PERMISSIONS = 1;
//    val IMAGE_CAPTURE_CODE = 2;
//    private val OPERATION_CAPTURE_PHOTO = 3
//    private val OPERATION_CHOOSE_PHOTO = 4
//
//    //uri of photo
//    private var uri: Uri? = null
//
//    //btns
//    private var capturedImage: ImageView? = null
//    private lateinit var btnTakePicture: Button
//    private lateinit var btnGallery : Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//    //initialize xml stuff
//        btnTakePicture = findViewById(R.id.btnTakePicture)
//        btnGallery = findViewById(R.id.btnGallery)
//        capturedImage = findViewById(R.id.capturedImage)
//
//        btnTakePicture.setOnClickListener {
//            var permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//
//            if(permission != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(android.Manifest.permission.CAMERA),REQUEST_CAMERA_PERMISSIONS)
//            } else {
////                val cIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////                startActivityForResult(cIntent, IMAGE_CAPTURE_CODE)
//                capturePhoto()
//            }
//        }
//        btnGallery.setOnClickListener{
//            val checkSelfPermission = ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
//                //Requests permissions to be granted to this application at runtime
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
//            }
//            else{
//                openGallery()
//            }
//        }
//    }
//    //gets snapshot
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            OPERATION_CAPTURE_PHOTO ->
//                if (resultCode == Activity.RESULT_OK) {
//                    val bitmap = BitmapFactory.decodeStream(
//                        getContentResolver().openInputStream(uri!!))
//                    capturedImage!!.setImageBitmap(bitmap)
//                }
//            OPERATION_CHOOSE_PHOTO ->
//                if (resultCode == Activity.RESULT_OK) {
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        handleImageOnKitkat(data)
//                    }
//                }
//        }
//    }
//
//    //capture the photo and store
//    private fun capturePhoto(){
//        val capturedPhoto = File(externalCacheDir, "Photo1.jpg")
//        if(capturedPhoto.exists()) {
//            capturedPhoto.delete()
//        }
//        //create new file
//        capturedPhoto.createNewFile()
//        uri = if(Build.VERSION.SDK_INT >= 24){
//            FileProvider.getUriForFile(this, "com.example.fridgetracker.activities",
//                capturedPhoto)
//        } else {
//            Uri.fromFile(capturedPhoto)
//        }
//
//        val intent = Intent("android.media.action.IMAGE_CAPTURE")
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
//        startActivityForResult(intent, OPERATION_CAPTURE_PHOTO)
//    }
//    //go to gallery
//    private fun openGallery(){
//        val intent = Intent("android.intent.action.GET_CONTENT")
//        intent.type = "image/*"
//        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
//    }
//
//    private fun renderImage(imagePath: String?){
//        if (imagePath != null) {
//            val bitmap = BitmapFactory.decodeFile(imagePath)
//            capturedImage?.setImageBitmap(bitmap)
//        }
//        else {
//            Toast.makeText(this,"imagePath is Null",Toast.LENGTH_SHORT).show()
//        }
//    }
//
////get image path for
//    private fun getImagePath(uri: Uri?, selection: String?): String {
//        var path: String? = null
//        val cursor = contentResolver.query(uri!!, null, selection, null, null )
//        if (cursor != null){
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
//            }
//            cursor.close()
//        }
//        return path!!
//    }
//
//    @TargetApi(19)
//    private fun handleImageOnKitkat(data: Intent?) {
//        var imagePath: String? = null
//        val uri = data!!.data
//        //DocumentsContract defines the contract between a documents provider and the platform.
//        if (DocumentsContract.isDocumentUri(this, uri)){
//            val docId = DocumentsContract.getDocumentId(uri)
//            if ("com.android.providers.media.documents" == uri!!.authority){
//                val id = docId.split(":")[1]
//                val selsetion = MediaStore.Images.Media._ID + "=" + id
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    selsetion)
//            }
//            else if ("com.android.providers.downloads.documents" == uri.authority){
//                val contentUri = ContentUris.withAppendedId(Uri.parse(
//                    "content://downloads/public_downloads"), java.lang.Long.valueOf(docId))
//                imagePath = getImagePath(contentUri, null)
//            }
//        }
//        else if ("content".equals(uri!!.scheme, ignoreCase = true)){
//            imagePath = getImagePath(uri, null)
//        }
//        else if ("file".equals(uri!!.scheme, ignoreCase = true)){
//            imagePath = uri!!.path
//        }
//        renderImage(imagePath)
//    }
//}