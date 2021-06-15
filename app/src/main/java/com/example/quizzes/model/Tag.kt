package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val name: String,
    val primary: Boolean,
    val type: String,
    val uid: Long
) : Parcelable