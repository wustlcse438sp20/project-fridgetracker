package com.example.fridgetracker.viewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fridgetracker.data.Recipe
import com.example.fridgetracker.network.RecipeRepository


class RecipeViewModel (application: Application): AndroidViewModel(application) {

    var recipeList: MutableLiveData<List<Recipe>> = MutableLiveData()
    var recipeRepository: RecipeRepository = RecipeRepository()
    var recipe: MutableLiveData<Recipe> = MutableLiveData()


    fun getRandomRecipe() {
        recipeRepository.getRandomRecipe()
        recipeList = recipeRepository.resBody
    }

    fun getByName(input : String) {
        recipeRepository.getByName(input)
        recipeList = recipeRepository.resBody
    }

//    fun getTopRanking() {
//        musicRepository.getTopRanking()
//        musicList = musicRepository.resBody
//    }
//
//    fun getBySearch(input: String) {
//        musicRepository.getBySearch(input)
//        musicList = musicRepository.resBody
//    }
//
//    fun getTrackInfo(track: String) {
//        musicRepository.getTrackInfo(track)
//        musicTrack = musicRepository.resBod
//
//    }

}