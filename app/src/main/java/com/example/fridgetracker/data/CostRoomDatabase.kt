package com.example.fridgetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// wah if I change the database of any kind, try changing the version number to reset the database; fallbackToDestructiveMigration
@Database(entities = arrayOf(Cost::class), version = 18)
public abstract class CostRoomDatabase : RoomDatabase() {

    abstract fun costDao(): CostDao

    //singleton pattern
    companion object {

        @Volatile
        private var INSTANCE: CostRoomDatabase? = null

        fun getDatabase(context: Context) : CostRoomDatabase {
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CostRoomDatabase::class.java,
                    "Cost_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}