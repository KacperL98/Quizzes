package com.example.quizzes.model3

data class Question(
    val answer: String,
    val answers: List<Answer>,
    val image: ImageX,
    val order: Int,
    val text: String,
    val type: String
)