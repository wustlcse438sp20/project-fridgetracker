package com.example.fridgetracker.network

import com.example.fridgetracker.data.Recipe
import com.example.fridgetracker.data.MealsWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RecipeInterface {
    @GET("api/json/v1/1/random.php")
    suspend fun getRandomRecipe() : Response<MealsWrapper>

    @GET("api/json/v1/1/search.php")
    suspend fun getByName(@Query("s") q : String) : Response<MealsWrapper>

    @GET
    suspend fun getRecipeInfo(@Url url: String) : Response<MealsWrapper>

//    @GET("api/json/v1/1/filter.php")
//    suspend fun getByIngredient(@Query("i") q : String) : Response<mealsWrapper>

    // need to make new data class for this one?
//    @GET("api/json/v1/1/filter.php")
//    suspend fun getByIngredient(@Query("i") q : String) : Response<mealsWrapper>


//
//    @GET("search")
//    suspend fun getBySearch(@Query("q") q : String) : Response<mealsWrapper>
//
//    @GET("chart/0/tracks")
//    suspend fun getTopRanking() : Response<mealsWrapper>

}