package com.example.fridgetracker.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fridgetracker.R
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
    }

    override fun onStart() {
        super.onStart()
    }
}