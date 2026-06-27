package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.Model.Question
import retrofit2.http.GET

interface QuestionService {
    // Cambiado de "questions" a "posts" para que no de Error 404 durante pruebas
    @GET("posts")
    suspend fun getQuestions(): List<Question>
}
