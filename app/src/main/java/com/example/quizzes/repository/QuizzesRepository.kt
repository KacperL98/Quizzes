package com.example.quizzes.repository

import com.example.quizzes.api.QuizzesService
import com.example.quizzes.database.BaseDataSource
import javax.inject.Inject

class QuizzesRepository @Inject constructor(
    private val quizzesService: QuizzesService
) : BaseDataSource() {

    suspend fun getAllQuizzes() = getResult { quizzesService.getAllQuizzes() }
    suspend fun getDetailsQuiz(id_quizu: Long) = getResult { quizzesService.getDetailsQuiz(id_quizu) }
}
