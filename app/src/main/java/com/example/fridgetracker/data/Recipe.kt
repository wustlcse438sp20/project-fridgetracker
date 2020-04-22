package com.example.fridgetracker.data

import androidx.room.Entity
import java.io.Serializable
import androidx.room.*

data class Recipe(
    val idMeal: String,
    val strMeal: String, // name
    val strInstructions: String,
    val strMealThumb: String // image url
//    val strIngredient1: String,
//    val strMeasure1: String

// the database has strIngredient1...strIngredient20 and strMeasure1...strMeasure20 which can be ""

): Serializable

data class MealsWrapper(
    val meals: List<Recipe>
)

@Entity(tableName = "savedRecipes")
data class SavedRecipe(
    @ColumnInfo(name = "idMeal")
    val idMeal: String,
    @ColumnInfo(name = "strMeal")
    val strMeal: String,
    @ColumnInfo(name = "strInstructions")
    val strInstructions: String
//    @ColumnInfo(name = "foodQuantity")
//    val foodQuantity: Int,
//    @ColumnInfo(name = "foodNote")
//    val foodNote: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

// this class may not be necessary
data class RecipeImage(
    val strMealThumb: String, // url
    val strMeal: String // name of recipe
) : Serializable