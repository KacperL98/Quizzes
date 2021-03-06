package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val height: String,
    val source: String,
    val url: String,
    val width: String
) : Parcelable