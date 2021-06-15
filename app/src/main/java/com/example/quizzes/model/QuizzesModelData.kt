package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizzesModelData(
    val count: Int,
    val items: List<Item>
) : Parcelable

