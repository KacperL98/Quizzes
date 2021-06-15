package com.example.quizzes.model3

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class Root(
    val avgResult: Double,
    val buttonStart: String,
    val canonical: String,
    val categories: List<Category>,
    val category: CategoryX,
    val celebrity: Celebrity,
    val cityAvg: Any, //typeConverter
    val cityCount: Any,
    val cityTime: Any,
    val content: String,
    val created: Int,
    val createdAt: String,
    val id: Long,
    val isBattle: Boolean,
    val latestResults: List<LatestResult>,
    val mainPhoto: MainPhoto,
    val opinions_enabled: Boolean,
    val productUrl: String,
    val publishedAt: String,
    val questions: List<Question>,
    val rates: List<Rate>,
    val resultCount: Int,
    val scripts: String,
    val shareTitle: String,
    val sponsored: Boolean,
    val sponsoredResults: SponsoredResults,
    val tags: List<Tag>,
    val title: String,
    val type: String,
    val userBattleDone: Boolean
)