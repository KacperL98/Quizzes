package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryX(
    val id: Long,
    val name: String
) : Parcelable