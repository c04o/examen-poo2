package com.example.examenpoo2.backend.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<AnswerOption>
)

data class AnswerOption(
    val text: String,
    val scores: List<Score>
)

data class Score(
    val area: String,
    val points: Int
)
