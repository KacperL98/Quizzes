package com.example.quizzes

import androidx.room.TypeConverter
import com.example.quizzes.model.QuizzesModelData
import com.example.quizzes.model.RoomData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverterData {
    @TypeConverter
    fun toProducts(value: String): RoomData {
        val notesType = object : TypeToken<RoomData>() {}.type
        return Gson().fromJson(value, notesType)
    }

    @TypeConverter
    fun fromProducts(value: RoomData): String {
        return Gson().toJson(value)
    }
}