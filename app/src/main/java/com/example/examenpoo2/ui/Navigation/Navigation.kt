package com.example.examenpoo2.ui.Navigation

import kotlinx.serialization.Serializable

/**
 * Representa la ruta hacia la pantalla de inicio de sesión o registro.
 */
@Serializable
object LoginRoute

/**
 * Representa la ruta hacia la pantalla de bienvenida post-autenticación.
 */
@Serializable
object WelcomeRoute

/**
 * Representa la ruta hacia el historial de resultados.
 */
@Serializable
object HistoryRoute

/**
 * Representa la ruta hacia el flujo principal del cuestionario vocacional.
 */
@Serializable
object QuestionListRoute

/**
 * Representa la ruta para ver el detalle de una pregunta específica.
 * 
 * @property questionId Identificador único de la pregunta a mostrar.
 */
@Serializable
data class QuestionDetailRoute(val questionId: Int)

/**
 * Representa la ruta hacia la pantalla de resultados finales.
 * 
 * @property resultsJson A map of area names to points, serialized as a JSON string.
 */
@Serializable
data class ResultRoute(
    val resultsJson: String
)
