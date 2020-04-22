package com.example.fridgetracker.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodRepository(private val foodDao: FoodDao) {

    val allFood: LiveData<List<Food>> = foodDao.getFoodItems()

    //add new foodItem
    fun insertFoodItem(food: Food) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.insertFood(food)
        }
    }


    //delete food item
    fun deleteFoodItem(food: Food) {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.deleteFoodItem(food)
        }
    }

    // simply get all playlists; this is unrelated to the SQL join
    fun getAllFood() {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.getFoodItems()
        }
    }

    fun getFridgeItems(): LiveData<List<Food>>{
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.getFridgeItems()
        }
        return foodDao.getFridgeItems()
    }
    fun getFreezerItems(): LiveData<List<Food>>{
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.getFreezerItems()
        }
        return foodDao.getFreezerItems()
    }

    fun getPantryItems(): LiveData<List<Food>>{
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.getPantryItems()
        }
        return foodDao.getPantryItems()
    }

    fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            foodDao.deleteAll()
        }
    }

}