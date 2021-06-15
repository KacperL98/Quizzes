package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlagResult(
    val content: String,
    val flag: String,
    val image: Image,
    val title: String
) : Parcelable