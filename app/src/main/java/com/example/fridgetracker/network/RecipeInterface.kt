package com.example.fridgetracker.network

import com.example.fridgetracker.data.Recipe
import com.example.fridgetracker.data.mealsWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RecipeInterface {
    @GET("api/json/v1/1/random.php")
    suspend fun getRandomRecipe() : Response<mealsWrapper>

//    @GET
//    suspend fun getTrackInfo(@Url url: String) : Response<Recipe>
//
//    @GET("search")
//    suspend fun getBySearch(@Query("q") q : String) : Response<mealsWrapper>
//
//    @GET("chart/0/tracks")
//    suspend fun getTopRanking() : Response<mealsWrapper>

}