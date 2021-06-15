package com.example.quizzes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val buttonStart: String,
    val categories: List<Category>,
    val category: CategoryX,
    val content: String,
    val createdAt: String,
    val flagResults: List<FlagResult>,
    val id: Long,
    val mainPhoto: MainPhoto,
    val productUrls: ProductUrls,
    val publishedAt: String,
    val questions: Int,
    val shareTitle: String,
    val sponsored: Boolean,
    val tags: List<Tag>,
    val title: String,
    val type: String
) : Parcelable