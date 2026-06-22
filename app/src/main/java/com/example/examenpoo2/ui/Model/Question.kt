package com.example.examenpoo2.ui.Model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val text: String,
    val options: List<AnswerOption>
)

@Serializable
data class AnswerOption(
    val id: Int,
    val text: String,
    val category: String
)
