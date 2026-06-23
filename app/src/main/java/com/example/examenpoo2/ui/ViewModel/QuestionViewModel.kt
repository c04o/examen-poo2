package com.example.examenpoo2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.repository.QuestionRepository
import com.example.examenpoo2.ui.service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<QuestionListState>(QuestionListState.Loading)
    val uiState: StateFlow<QuestionListState> = _uiState

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            _uiState.value = QuestionListState.Loading
            when (val result = repository.getQuestions()) {
                is ApiResult.Success -> _uiState.value = QuestionListState.Success(result.data)
                is ApiResult.Error -> _uiState.value = QuestionListState.Error(result.message)
                is ApiResult.Loading -> _uiState.value = QuestionListState.Loading
            }
        }
    }
}
