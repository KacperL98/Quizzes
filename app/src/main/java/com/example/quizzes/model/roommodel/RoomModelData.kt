package com.example.quizzes.model.roommodel

import android.os.Parcelable
import com.example.quizzes.model.MainPhoto
import com.example.quizzes.model.Question
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomModelData(
    val id: Long = 0,
    val mainPhoto: MainPhoto = MainPhoto(),
    var questions: List<Question> = listOf(),
    val title: String="",
    var quizResult: Int = 0
) : Parcelable