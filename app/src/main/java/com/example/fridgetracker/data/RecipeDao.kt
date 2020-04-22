package com.example.fridgetracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM savedRecipes")
    fun getAllRecipes(): LiveData<List<SavedRecipe>>

    @Insert
    fun insertRecipe(recipe: SavedRecipe)

    @Query("DELETE FROM savedRecipes WHERE savedRecipes.idMeal=:idMeal")
    fun deleteRecipe(idMeal:String)

    //
    @Query("DELETE FROM savedRecipes")
    fun deleteAll()
//
}