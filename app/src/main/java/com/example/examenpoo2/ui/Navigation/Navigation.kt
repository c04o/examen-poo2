package com.example.examenpoo2.ui.Navigation

import kotlinx.serialization.Serializable

/**
 * Navigation.kt: Define los destinos de navegación de la aplicación de forma segura (Type-Safe).
 * Cada objeto o clase anotada con @Serializable representa una pantalla o ruta única.
 */

/**
 * Ruta para la pantalla de inicio de sesión o bloqueo (LockScreen).
 * Es el punto de entrada principal para la identificación del usuario.
 */
@Serializable
object LoginRoute

/**
 * Ruta para la pantalla de bienvenida (WelcomeScreen).
 * Se muestra tras un inicio de sesión exitoso.
 */
@Serializable
object WelcomeRoute

/**
 * Ruta para la pantalla que muestra la lista de preguntas del test (QuestionListScreen).
 * Esta pantalla gestiona la carga de datos desde la API.
 */
@Serializable
object QuestionListRoute

/**
 * Ruta para la pantalla de detalle de una pregunta específica.
 * @property questionId Identificador de la pregunta que se desea visualizar.
 */
@Serializable
data class QuestionDetailRoute(val questionId: Int)
