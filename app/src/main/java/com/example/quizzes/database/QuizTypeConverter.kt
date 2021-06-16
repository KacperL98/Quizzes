package com.example.quizzes.database

import androidx.room.TypeConverter
import com.example.quizzes.model.roommodel.RoomListData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuizTypeConverter {
    @TypeConverter
    fun toQuizzes(value: String): RoomListData {
        val notesType = object : TypeToken<RoomListData>() {}.type
        return Gson().fromJson(value, notesType)
    }
    @TypeConverter
    fun fromQuizzes(value: RoomListData): String {
        return Gson().toJson(value)
    }
}