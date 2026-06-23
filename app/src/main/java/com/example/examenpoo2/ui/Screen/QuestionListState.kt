package com.example.examenpoo2.ui.screen


import com.example.examenpoo2.ui.model.Question

data class QuestionListState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: String? = null
)
