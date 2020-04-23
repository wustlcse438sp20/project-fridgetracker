package com.example.cse438.cse438_assignment4.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fridgetracker.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.android.synthetic.main.signup_fragment.view.*


class SignUpFragment() : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    lateinit var username: EditText
    private val TAG = "LoginFragment"

    lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.signup_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = view.findViewById<EditText>(R.id.email)
        password = view.findViewById<EditText>(R.id.password)
        username = view.findViewById<EditText>(R.id.username)

        view.signupButton.setOnClickListener { view ->
            if (email.text.toString() != "" && password.text.toString() != ""){
                Toast.makeText(
                    getActivity(), "Authenticating",
                    Toast.LENGTH_SHORT
                ).show()
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(
                                getActivity(), "Authentication Success",
                                Toast.LENGTH_SHORT
                            ).show()

                            //authenticate new user
                            val userAuth = auth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(username.text.toString())
                                .build()

                            //add username to user authentication
                            userAuth?.updateProfile(profileUpdates)
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "User profile updated.")
                                    }
                                }

                            //add user to database
                            val receiptsUrl = arrayListOf<String>()
                            val user = hashMapOf(
                                "email" to email.text.toString(),
//                                "password" to password.text.toString(),
                                "receiptsUrl" to receiptsUrl
                            )
//                            //println(user)
                            database.collection("users")
                                .add(user)
                                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                getActivity(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
            else{
                Toast.makeText(
                    getActivity(), "Fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

//

}