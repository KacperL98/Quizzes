package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Image(
    val author: String,
    val height: Int,
    val source: String,
    val url: String,
    val width: Int
) : Parcelable