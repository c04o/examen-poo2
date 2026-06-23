package com.example.examenpoo2.ui.viewmodel

import com.example.examenpoo2.ui.model.Question

sealed interface QuestionListState {
    data object Loading : QuestionListState
    data class Success(val questions: List<Question>) : QuestionListState
    data class Error(val message: String) : QuestionListState
}
