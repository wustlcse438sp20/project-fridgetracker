package com.example.fridgetracker.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fridgetracker.R
import com.example.fridgetracker.data.Recipe
import com.example.fridgetracker.data.SavedRecipe
import com.example.fridgetracker.viewModels.RecipeViewModel
import kotlinx.android.synthetic.main.recipe_info.*


class RecipeInfo: AppCompatActivity() {
    private lateinit var viewModel: RecipeViewModel
    var id : Int = 0
    var comingFrom : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_info)
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        // get intent from RecipeListAdapter
        val intent = intent
        id = intent!!.getIntExtra("idMeal", 0)
        comingFrom = intent!!.getStringExtra("comingFrom")
        println("id: " + id)
        viewModel.getRecipeInfo("https://www.themealdb.com/api/json/v1/1/lookup.php?i="+id)

        viewModel!!.recipe.observe(this, Observer { recipe: Recipe? ->
            println("inside observe")
            // load picture into imageview?
            //Picasso.get().load(recipe?.strMealThumb).into(albumInfo) // ImageView
            recipeSaveTitle.text = recipe?.strMeal
            //ingredients.text = "Ingredients: "+ recipe?.strIngredient1
            directions.text = "Directions: " + recipe?.strInstructions

            saveRecipeButton.setOnClickListener {
                println("save button went through")
                val savedRecipe = SavedRecipe(
                    recipe!!.idMeal,
                    recipe!!.strMeal,
                    recipe!!.strInstructions
                )
                // insert recipe into saved recipes
                viewModel!!.insertRecipe(savedRecipe)
            }
            deleteRecipeButton.setOnClickListener {
                println("delete button went through")
                val savedRecipe = SavedRecipe(
                    recipe!!.idMeal,
                    recipe!!.strMeal,
                    recipe!!.strInstructions
                )
                // insert recipe into saved recipes
                viewModel!!.deleteRecipe(recipe!!.idMeal)
            }

        })



    }


    fun backToRecipeSearch(view: View){
        var intent = Intent(this, RecipeActivity::class.java)
//        main.putExtra("id", 1)
        startActivity(intent)
        finish()
    }


}
