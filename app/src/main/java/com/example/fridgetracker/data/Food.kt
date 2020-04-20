package com.example.fridgetracker.data

import android.widget.DatePicker
import androidx.room.*
import java.util.*

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
//class Converters {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return value?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): Long? {
//        return date?.time?.toLong()
//    }
//}