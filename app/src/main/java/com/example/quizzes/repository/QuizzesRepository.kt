package com.example.quizzes.repository

import com.example.quizzes.api.QuizApi
import javax.inject.Inject

class QuizzesRepository @Inject constructor(
    private val quizzesService: QuizApi
)  {
    suspend fun getAllQuizzesApi() =  quizzesService.getAllListQuiz()
    suspend fun getDetailsQuiz(idQuiz: Long) =   quizzesService.getDetailsQuiz(idQuiz)
}
