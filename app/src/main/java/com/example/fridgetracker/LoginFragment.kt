package com.example.cse438.cse438_assignment4.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fridgetracker.R
import com.example.fridgetracker.activities.ContentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class LoginFragment() : Fragment() {
    lateinit var email: EditText
    lateinit var password: EditText
    private val TAG = "SignUpFragment"

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.login_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = view.findViewById<EditText>(R.id.email)
        password = view.findViewById<EditText>(R.id.password)
        auth = FirebaseAuth.getInstance()
        var loginButton = view.findViewById(R.id.loginButton) as Button

        loginButton.setOnClickListener {
            if (email.text.toString()!=""&& password.text.toString()!=""){
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            var intent = Intent(getActivity(), ContentActivity::class.java)
                            intent.putExtra("id", auth?.currentUser?.displayName)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                getActivity(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
            else{
                Toast.makeText(
                    getActivity(), "Fill in all fields.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
//
}
