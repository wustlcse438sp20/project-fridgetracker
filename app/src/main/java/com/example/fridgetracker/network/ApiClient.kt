package com.example.fridgetracker.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    const val BASE_URL = "https://www.themealdb.com/"

    fun makeRetrofitService(): RecipeInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(RecipeInterface::class.java)

    }
}