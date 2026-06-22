package com.example.examenpoo2.models

// Aplicación de POO: Clases de datos para modelar el dominio de la aplicación
data class Question(
    val id: Int,
    val text: String,
    val options: List<AnswerOption>
)

data class AnswerOption(
    val text: String,
    val pointsEngineering: Int,
    val pointsArts: Int,
    val pointsHealth: Int
)