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
 * @property engineeringScore Puntaje total acumulado en el área de Ingeniería.
 * @property artsScore Puntaje total acumulado en el área de Artes.
 * @property healthScore Puntaje total acumulado en el área de Salud.
 */
@Serializable
data class ResultRoute(
    val engineeringScore: Int,
    val artsScore: Int,
    val healthScore: Int
)
