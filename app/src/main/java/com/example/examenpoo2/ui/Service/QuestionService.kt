package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.Model.Question
import retrofit2.http.GET

/**
 * QuestionService: Interfaz que define los puntos de entrada (endpoints) de la API.
 * Retrofit utiliza esta interfaz para generar automáticamente el código necesario
 * para realizar las peticiones HTTP.
 */
interface QuestionService {
    
    /**
     * Obtiene la lista completa de preguntas para el test vocacional.
     * 
     * @return Una lista de objetos [Question]. 
     * Se usa 'suspend' para que la petición se ejecute de forma asíncrona sin bloquear la UI.
     */
    @GET("questions") // El nombre del recurso en tu servidor backend
    suspend fun getQuestions(): List<Question>
}
