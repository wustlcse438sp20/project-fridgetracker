package com.example.fridgetracker.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CostRepository(private val costDao: CostDao) {

    val allCosts: LiveData<List<Cost>> = costDao.getAllCosts()

    //add new foodItem
    fun insertCost(cost: Cost) {
        CoroutineScope(Dispatchers.IO).launch {
            costDao.insertCost(cost)
        }
    }


    //delete cost item
    fun deleteCost(cost: Cost) {
        CoroutineScope(Dispatchers.IO).launch {
            costDao.deleteCost(cost)
        }
    }
//    fun deleteAll() {
//        CoroutineScope(Dispatchers.IO).launch {
//            costDao.deleteAll()
//        }
//    }

    // simply get all costs; this is unrelated to the SQL join
    fun getAllCosts() {
        CoroutineScope(Dispatchers.IO).launch {
            costDao.getAllCosts()
        }
    }


//    fun clear() {
//        CoroutineScope(Dispatchers.IO).launch {
//            costDao.deleteAll()
//        }
//    }

}