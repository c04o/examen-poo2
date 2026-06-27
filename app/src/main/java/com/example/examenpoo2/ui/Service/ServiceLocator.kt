package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.Repository.QuestionRepository

/**
 * ServiceLocator: Implementación sencilla del patrón Service Locator para la Inyección de Dependencias.
 * Centraliza la creación y provisión de servicios y repositorios en toda la aplicación.
 */
object ServiceLocator {

    /**
     * Instancia de [QuestionService] creada a través de la instancia de Retrofit.
     */
    private val questionService: QuestionService by lazy {
        // Usamos .create() para obtener la implementación de la interfaz
        RetrofitClient.instance.create(QuestionService::class.java)
    }

    /**
     * Repositorio encargado de la lógica de negocio de las preguntas.
     */
    val questionRepository: QuestionRepository by lazy {
        QuestionRepository(questionService)
    }
}
