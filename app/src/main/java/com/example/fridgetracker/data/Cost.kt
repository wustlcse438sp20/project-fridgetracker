package com.example.fridgetracker.data

import androidx.room.*


@Entity(tableName = "listOfCosts")
data class Cost(
    @ColumnInfo(name = "dateOfPurchase")
    val dateOfPurchase:String,
    @ColumnInfo(name = "cost")
    val cost: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}