package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemQuizList(
    val id: Long,
    val mainPhoto: MainPhoto,
    val questions: Int = 0,
    val title: String,
) : Parcelable