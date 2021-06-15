package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val name: String,
    val secondaryCid: String,
    val type: String,
    val uid: Long
) : Parcelable