package com.example.examenpoo2.ui.Repository

import android.util.Log
import com.example.examenpoo2.ui.Model.Question
import com.example.examenpoo2.ui.Service.ApiResult
import com.example.examenpoo2.ui.Service.QuestionService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * QuestionRepository: Actúa como el mediador entre la fuente de datos (API remota) 
 * y el resto de la aplicación. Implementa la lógica necesaria para transformar
 * las respuestas de red en flujos de datos reactivos.
 */
class QuestionRepository(private val service: QuestionService) {

    /**
     * Solicita la lista de preguntas al servidor y gestiona los estados de la petición.
     * 
     * @return Un [Flow] que emite:
     * - [ApiResult.Loading] al iniciar el proceso.
     * - [ApiResult.Success] con la lista de preguntas si la operación es exitosa.
     * - [ApiResult.Error] si ocurre un fallo de red o de procesamiento.
     */
    fun getQuestions(): Flow<ApiResult<List<Question>>> = flow {
        // Notificamos a los observadores que la carga ha comenzado.
        emit(ApiResult.Loading)
        try {
            // Realizamos la llamada suspendida al servicio de Retrofit.
            val result = service.getQuestions()
            // Si hay éxito, emitimos el resultado envuelto en el estado Success.
            emit(ApiResult.Success(result))
        } catch (e: Exception) {
            // Registramos el error en el Logcat para depuración.
            Log.e("QuestionRepository", "Error al obtener preguntas del test", e)
            // Emitimos el estado de error con un mensaje descriptivo para el usuario.
            emit(ApiResult.Error("Error al conectar con el servidor: ${e.localizedMessage}"))
        }
    }
}
