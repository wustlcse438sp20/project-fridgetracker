package com.example.fridgetracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("SELECT * FROM foodItems")
    fun getFoodItems(): LiveData<List<Food>>

    @Query("SELECT * FROM foodItems WHERE location = 'fridge' ")
    fun getFridgeItems(): LiveData<List<Food>>

    @Query("SELECT * FROM foodItems WHERE location = 'freezer' ")
    fun getFreezerItems(): LiveData<List<Food>>

    @Query("SELECT * FROM foodItems WHERE location = 'pantry' ")
    fun getPantryItems(): LiveData<List<Food>>

    @Insert
    fun insertFood(food: Food)

    @Delete
    fun deleteAllFoods(food: Food)

    @Delete
    fun deleteFoodItem(food:Food)
//
    @Query("DELETE FROM foodItems")
    fun deleteAll()
//
}