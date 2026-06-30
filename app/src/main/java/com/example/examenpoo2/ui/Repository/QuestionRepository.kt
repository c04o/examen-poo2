package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.Question
import com.example.examenpoo2.ui.Service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * QuestionRepository: Clase encargada de gestionar los datos de las preguntas.
 * 
 * Se ha modificado para obtener las preguntas desde [LocalQuestionProvider]
 * en lugar de realizar una petición de red, cumpliendo con el requisito de 
 * tener los datos guardados en el sistema local.
 */
class QuestionRepository {
    fun getQuestions(): Flow<ApiResult<List<Question>>> = flow {
        emit(ApiResult.Loading)
        try {
            // Obtenemos las preguntas del proveedor local
            val response = LocalQuestionProvider.getQuestions()
            emit(ApiResult.Success(response))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Error al cargar preguntas locales", e))
        }
    }
}
