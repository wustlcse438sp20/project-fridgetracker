package com.example.fridgetracker.viewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fridgetracker.data.*
import com.example.fridgetracker.network.RecipeRetrofitRepository


class RecipeViewModel (application: Application): AndroidViewModel(application) {

    var recipeList: MutableLiveData<List<Recipe>> = MutableLiveData()
    var recipeRetrofitRepository: RecipeRetrofitRepository = RecipeRetrofitRepository()
    var recipe: MutableLiveData<Recipe> = MutableLiveData()


    fun getRandomRecipe() {
        recipeRetrofitRepository.getRandomRecipe()
        recipeList = recipeRetrofitRepository.resBody
    }

    fun getByName(input : String) {
        recipeRetrofitRepository.getByName(input)
        recipeList = recipeRetrofitRepository.resBody
    }

    fun getRecipeInfo(url: String) {
        recipeRetrofitRepository.getRecipeInfo(url)
        recipe = recipeRetrofitRepository.resBodySingleItem
//        recipe = recipeRetrofitRepository.resBody
    }

    // ------------------------ Database methods ----------------------------------
    var _savedRecipesList: LiveData<List<SavedRecipe>> = MutableLiveData()

    private val repository: RecipeRepository

    init {
        repository = RecipeRepository(RecipeRoomDatabase.getDatabase(application).recipeDao())
        _savedRecipesList = repository.allRecipes
    }

    fun getAllRecipes() : LiveData<List<SavedRecipe>> {
        return _savedRecipesList
    }


    fun insertRecipe(savedRecipe: SavedRecipe) {
        repository.insertRecipe(savedRecipe)
    }


    fun deleteRecipe(idMeal: String) {
        repository.deleteRecipe(idMeal)
    }


    fun clear() {
        repository.clear()
    }


}