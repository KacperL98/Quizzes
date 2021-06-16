package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizDetailsDataModel(
    val id: Long,
    val mainPhoto: MainPhoto?,
    val questions: List<Question> = listOf(),
    val title: String,
) : Parcelable