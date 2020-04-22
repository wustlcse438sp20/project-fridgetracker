package com.example.fridgetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// wah if I change the database of any kind, try changing the version number to reset the database; fallbackToDestructiveMigration
@Database(entities = arrayOf(Food::class), version = 18)
//@TypeConverters(Converters::class)
public abstract class FoodRoomDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    //singleton pattern
    companion object {

        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null

        fun getDatabase(context: Context) : FoodRoomDatabase {
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    "playlist_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}