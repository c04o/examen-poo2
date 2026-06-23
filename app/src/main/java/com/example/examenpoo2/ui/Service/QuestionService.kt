package com.example.examenpoo2.ui.service

import com.example.examenpoo2.ui.model.Question
import retrofit2.http.GET

interface QuestionService {
    @GET("questions")
    suspend fun getQuestions(): List<Question>
}
