package com.example.examenpoo2.ui.ViewModel

import com.example.examenpoo2.ui.Model.Question

data class QuestionDetailState(
    val isLoading: Boolean = false,
    val question: Question? = null,
    val error: String? = null
)
