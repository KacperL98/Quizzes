package com.example.quizzes.api

import com.example.quizzes.basic.Const.Companion.BASE_URL
import com.example.quizzes.model.QuizzesModelData
import com.example.quizzes.model3.Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizzesService {
    @GET("quizzes/0/100")
    suspend fun getAllQuizzes(): Response<QuizzesModelData>

    @GET("quiz/{id_quizu}/0")
    suspend fun getDetailsQuiz(
        @Path("id_quizu") id_quizu: Long
    ): Response<Root>

}
