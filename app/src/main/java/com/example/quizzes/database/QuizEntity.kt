package com.example.quizzes.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizzes.model.roommodel.RoomListData

@Entity(tableName = "ListItemEntity")
data class QuizEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val roomListData: RoomListData?
)