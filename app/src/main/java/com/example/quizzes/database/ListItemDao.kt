package com.example.quizzes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzes.model.Item
import com.example.quizzes.model3.Root
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemDao {

            @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listItemEntities: ListItemEntity)

    @Query("SELECT * FROM ListItemEntity")
    fun getAll(): Flow<ListItemEntity>


//    @Query("SELECT * FROM ListItemEntity WHERE ID LIKE '%' || :text || '%' OR title LIKE '%' || :text || '%' ")
//    fun searchListItemByPhrase(text: String): Flow<ListItemEntity>
}