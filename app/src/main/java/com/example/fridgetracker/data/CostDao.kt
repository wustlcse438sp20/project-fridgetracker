package com.example.fridgetracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CostDao {

    @Query("SELECT * FROM listOfCosts")
    fun getAllCosts(): LiveData<List<Cost>>

    @Insert
    fun insertCost(cost: Cost)

    @Delete
    fun deleteCost(cost:Cost)

    //
//    @Query("DELETE FROM listOfCosts")
//    fun deleteAll()
//
}