package com.example.fridgetracker.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fridgetracker.data.Cost
import com.example.fridgetracker.data.CostRepository
import com.example.fridgetracker.data.CostRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CostViewModel(application: Application): AndroidViewModel(application) {
    var _costItemList: LiveData<List<Cost>> = MutableLiveData()

    private val repository: CostRepository

    init {
        repository = CostRepository(CostRoomDatabase.getDatabase(application).costDao())
        _costItemList = repository.allCosts
    }

    fun getAllCosts() : LiveData<List<Cost>>{
        return _costItemList
    }


    fun insertCost(cost: Cost) {
        repository.insertCost(cost)
    }


    fun delete(cost: Cost) {
        repository.deleteCost(cost)
    }


    fun clear() {
        repository.clear()
    }



}