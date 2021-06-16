package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Answer(
    val image: Image,
    val isCorrect: Int,
    val order: Int,
    val text: String
) : Parcelable