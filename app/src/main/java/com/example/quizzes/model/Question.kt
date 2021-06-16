package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val answer: String,
    val answers: List<Answer>,
    val text: String,
    val image: Image
) : Parcelable