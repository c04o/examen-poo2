package com.example.examenpoo2.ui.repository

import com.example.examenpoo2.ui.model.Question
import com.example.examenpoo2.ui.service.ApiResult
import com.example.examenpoo2.ui.service.QuestionService


class QuestionRepository(private val service: QuestionService) {
    suspend fun getQuestions(): ApiResult<List<Question>> {
        return try {
            val result = service.getQuestions()
            ApiResult.Success(result)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Error desconocido")
        }
    }
}
