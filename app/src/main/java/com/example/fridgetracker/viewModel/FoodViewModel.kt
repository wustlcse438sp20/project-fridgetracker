package com.example.fridgetracker.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fridgetracker.data.Food
import com.example.fridgetracker.data.FoodRepository
import com.example.fridgetracker.data.FoodRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodViewModel(application: Application): AndroidViewModel(application) {
    var _foodItemList: LiveData<List<Food>> = MutableLiveData()

    private val repository: FoodRepository

    init {
        repository = FoodRepository(FoodRoomDatabase.getDatabase(application).foodDao())
        _foodItemList = repository.allFood
    }

    fun getAllFood() : LiveData<List<Food>>{
        return _foodItemList
    }

    fun getFridgeItems(): LiveData<List<Food>>{
        _foodItemList = repository.getFridgeItems()
        return repository.getFridgeItems()
    }

    fun getFreezerItems(): LiveData<List<Food>>{
        _foodItemList = repository.getFreezerItems()
        return repository.getFreezerItems()
    }
    fun getPantryItems(): LiveData<List<Food>>{
        _foodItemList = repository.getPantryItems()
        return repository.getPantryItems()
    }
    fun insertFood(food: Food) {
        repository.insertFoodItem(food)
    }

    fun deleteFoodItem(food:Food){
        repository.deleteFoodItem(food)
    }


    fun clear() {
        repository.clear()
    }



}