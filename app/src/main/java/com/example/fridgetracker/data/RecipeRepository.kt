package com.example.fridgetracker.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: LiveData<List<SavedRecipe>> = recipeDao.getAllRecipes()

    //add new recipe
    fun insertRecipe(recipe: SavedRecipe) {
        CoroutineScope(Dispatchers.IO).launch {
            recipeDao.insertRecipe(recipe)
        }
    }


    //delete recipe item
    fun deleteRecipe(idMeal: String) {
        CoroutineScope(Dispatchers.IO).launch {
            recipeDao.deleteRecipe(idMeal)
        }
    }

    // simply get all recipe; this is unrelated to the SQL join
    fun getAllRecipes() {
        CoroutineScope(Dispatchers.IO).launch {
            recipeDao.getAllRecipes()
        }
    }


    fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            recipeDao.deleteAll()
        }
    }

}