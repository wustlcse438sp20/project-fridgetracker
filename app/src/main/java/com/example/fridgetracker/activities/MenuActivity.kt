package com.example.fridgetracker.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cse438.cse438_assignment4.fragments.LoginFragment
import com.example.fridgetracker.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        inventoryButton.setOnClickListener {
            var intent = Intent(this, InventoryActivity::class.java)
            startActivity(intent)
        }

        recipeButton.setOnClickListener {
            var intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
        costManagerButton.setOnClickListener {
            var intent = Intent(this, ReceiptsActivity::class.java)
            startActivity(intent)
        }
        receiptsButton.setOnClickListener {
            var intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("send", 0)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
    }
}