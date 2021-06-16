package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainPhoto(
    val author: String ="",
    val height: Int = 0,
    val source: String = "",
    val title: String = "",
    val url: String ="",
    val width: Int = 0
) : Parcelable