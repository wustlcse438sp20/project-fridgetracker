package com.example.fridgetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// wah if I change the database of any kind, try changing the version number to reset the database; fallbackToDestructiveMigration
@Database(entities = arrayOf(SavedRecipe::class), version = 9)
public abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    //singleton pattern
    companion object {

        @Volatile
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getDatabase(context: Context) : RecipeRoomDatabase {
            val temp = INSTANCE
            if(temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeRoomDatabase::class.java,
                    "playlist_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}