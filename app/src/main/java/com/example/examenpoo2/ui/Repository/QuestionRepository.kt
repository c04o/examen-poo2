package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.Question
import com.example.examenpoo2.ui.Service.ApiResult
import com.example.examenpoo2.ui.Service.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * QuestionRepository: Clase encargada de gestionar los datos de las preguntas.
 * 
 * Ahora obtiene las preguntas desde el servidor backend para asegurar que los
 * datos sean dinámicos y no dependan de código local.
 */
class QuestionRepository {
    fun getQuestions(): Flow<ApiResult<List<Question>>> = flow {
        emit(ApiResult.Loading)
        try {
            // Se realiza la petición al backend a través de Retrofit
            val response = RetrofitClient.questionService.getQuestions()
            emit(ApiResult.Success(response))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Error al conectar con el servidor", e))
        }
    }
}
