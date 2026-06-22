package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.Question
import com.example.examenpoo2.ui.Service.QuestionService
import com.example.examenpoo2.ui.Service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuestionRepository(private val service: QuestionService) {
    fun getQuestions(): Flow<ApiResult<List<Question>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = service.getQuestions()
            emit(ApiResult.Success(response))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Error desconocido", e))
        }
    }
}
