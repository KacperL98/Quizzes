package com.example.quizzes.api

import com.example.quizzes.model.QuizModelData
import com.example.quizzes.model.QuizDetailsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizApi {
    @GET("quizzes/0/100")
    suspend fun getAllListQuiz(): Response<QuizModelData>

    @GET("quiz/{id_quizu}/0")
    suspend fun getDetailsQuiz(
        @Path("id_quizu") idQuiz: Long
    ): Response<QuizDetailsDataModel>
}
