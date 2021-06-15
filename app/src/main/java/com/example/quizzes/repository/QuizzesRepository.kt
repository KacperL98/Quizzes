package com.example.quizzes.repository

import com.example.quizzes.api.QuizzesService
import com.example.quizzes.database.BaseDataSource
import javax.inject.Inject

class QuizzesRepository @Inject constructor(
    private val quizzesService: QuizzesService
)  {

    suspend fun getAllQuizzes() =  quizzesService.getAllQuizzes()
    suspend fun getDetailsQuiz(id_quizu: Long) =   quizzesService.getDetailsQuiz(id_quizu)
}
