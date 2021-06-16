package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizModelData(
    val count: Int,
    val items: List<ItemQuizList>
) : Parcelable

