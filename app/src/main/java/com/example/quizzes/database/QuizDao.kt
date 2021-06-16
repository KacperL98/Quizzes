package com.example.quizzes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listItemEntities: QuizEntity)

    @Query("SELECT * FROM ListItemEntity")
    fun getAll(): Flow<QuizEntity>

}