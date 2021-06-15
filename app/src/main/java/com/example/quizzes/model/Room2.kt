package com.example.quizzes.model

import com.example.quizzes.model3.*

data class Room2(
    val id: Long = 0,
    val mainPhoto: com.example.quizzes.model.MainPhoto,
    var questions: List<Question> = listOf(),
    val title: String="",
    var quizResult: Int = 0
)