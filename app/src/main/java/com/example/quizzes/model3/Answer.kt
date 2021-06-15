package com.example.quizzes.model3

data class Answer(
    val image: Image,
    val isCorrect: Int,
    val order: Int,
    val text: String // odpowiedzi
)