package com.example.fridgetracker.network

import androidx.lifecycle.MutableLiveData
import com.example.fridgetracker.data.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response


class RecipeRepository {
    val service = ApiClient.makeRetrofitService()

    var resBody: MutableLiveData<List<Recipe>> = MutableLiveData()
    var resBod: MutableLiveData<Recipe> = MutableLiveData()

    fun getRandomRecipe() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getRandomRecipe()

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()?.meals
                        println(resBody.value)
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

    fun getByName(input: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getByName(input)

            withContext(Dispatchers.Main) {
                try{
                    if(response.isSuccessful) {
                        resBody.value = response.body()?.meals
                    }
                } catch (e: HttpException) {
                    println("Http error")
                }
            }
        }
    }

//    fun getTrackInfo(input:String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = service.getTrackInfo(input)
//
//            withContext(Dispatchers.Main) {
//                try{
//                    if(response.isSuccessful) {
//                        resBod.value = response.body()
//                        println(resBod.value)
//                    }
//                } catch (e: HttpException) {
//                    println("Http error")
//                }
//            }
//        }
//    }
//
//    fun getTopRanking() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = service.getTopRanking()
//
//            withContext(Dispatchers.Main) {
//                try{
//                    if(response.isSuccessful) {
//                        resBody.value = response.body()?.data
//                        println(resBody.value)
//                    }
//                } catch (e: HttpException) {
//                    println("Http error")
//                }
//            }
//        }
//    }
//    fun getBySearch(input:String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = service.getBySearch(input)
//
//            withContext(Dispatchers.Main) {
//                try{
//                    if(response.isSuccessful) {
//                        resBody.value = response.body()?.data
//                    }
//                } catch (e: HttpException) {
//                    println("Http error")
//                }
//            }
//        }
//    }
}