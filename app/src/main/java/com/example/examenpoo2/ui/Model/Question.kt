package com.example.examenpoo2.ui.Model

import kotlinx.serialization.Serializable

/**
 * Representa una pregunta dentro del examen vocacional.
 *
 * @property id Identificador único de la pregunta.
 * @property text El enunciado o texto de la pregunta que se mostrará al usuario.
 * @property options Lista de posibles respuestas ([AnswerOption]) asociadas a esta pregunta.
 */
@Serializable
data class Question(
    val id: Int,
    val text: String,
    val options: List<AnswerOption>
)

/**
 * Representa una opción de respuesta individual y el peso que tiene para cada perfil profesional.
 *
 * @property text El texto descriptivo de la respuesta.
 * @property pointsEngineering Puntos asignados al perfil de Ingeniería si se elige esta opción.
 * @property pointsArts Puntos asignados al perfil de Artes si se elige esta opción.
 * @property pointsHealth Puntos asignados al perfil de Salud si se elige esta opción.
 */
@Serializable
data class AnswerOption(
    val text: String,
    val pointsEngineering: Int,
    val pointsArts: Int,
    val pointsHealth: Int
)
