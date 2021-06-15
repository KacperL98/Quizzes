package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class RoomData(
    val count: Int,
    val items: List<Room2>
)
