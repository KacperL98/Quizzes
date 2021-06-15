package com.example.quizzes.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizzes.basic.Const
import com.example.quizzes.model.QuizzesModelData
import com.example.quizzes.model.Room2
import com.example.quizzes.model.RoomData
import com.example.quizzes.model3.Root

@Entity (tableName = "ListItemEntity")
data class ListItemEntity(
    @NonNull
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val root: RoomData?
    )