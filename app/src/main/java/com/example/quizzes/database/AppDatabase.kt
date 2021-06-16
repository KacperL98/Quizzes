package com.example.quizzes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [QuizEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverterData::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listItemDao(): QuizDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return (instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "app_database"
                ).build()
                instance = newInstance
                newInstance
            })
        }
    }
}