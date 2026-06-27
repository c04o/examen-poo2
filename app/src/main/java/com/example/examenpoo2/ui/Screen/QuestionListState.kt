package com.example.examenpoo2.ui.Screen


import com.example.examenpoo2.ui.Model.Question

data class QuestionListState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: String? = null
)
