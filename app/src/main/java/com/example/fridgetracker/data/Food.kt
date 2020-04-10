package com.example.fridgetracker.data

import androidx.room.*

@Entity(tableName = "foodItems")
data class Food(
    @ColumnInfo(name = "location")
    val location:String,
    @ColumnInfo(name = "foodName")
    val foodName: String,
    @ColumnInfo(name = "foodDate")
    val foodDate: String,
    @ColumnInfo(name = "foodQuantity")
    val foodQuantity: Int,
    @ColumnInfo(name = "foodNote")
    val foodNote: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
