package com.example.quizzes.model.roommodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomListData(
    val count: Int,
    val items: List<RoomModelData>
) : Parcelable
