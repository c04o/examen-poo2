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
 * @property scores Lista de puntuaciones asociadas a diferentes áreas vocacionales.
 */
@Serializable
data class AnswerOption(
    val text: String,
    val scores: List<Score>
)

/**
 * Representa una puntuación asignada a un área específica.
 *
 * @property area El nombre del área vocacional (ej: "Ingeniería").
 * @property points La cantidad de puntos asignados.
 */
@Serializable
data class Score(
    val area: String,
    val points: Int
)
