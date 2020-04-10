package com.example.fridgetracker.data

import java.io.Serializable

data class Recipe(
    val idMeal: String,
    val strMeal: String, // name
//    val strCategory: String,
//    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String // image url
//    val strIngredient1: String,
//    val strMeasure1: String

// the database has strIngredient1...strIngredient20 and strMeasure1...strMeasure20 which can be ""

): Serializable

data class mealsWrapper(
    val meals: List<Recipe>
)

// this class may not be necessary
data class RecipeImage(
    val strMealThumb: String, // url
    val strMeal: String // name of recipe
) : Serializable