package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.Model.Question
import retrofit2.http.GET

interface QuestionService {
    @GET("questions")
    suspend fun getQuestions(): List<Question>
}
