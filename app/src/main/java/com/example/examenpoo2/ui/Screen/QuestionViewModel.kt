package com.example.examenpoo2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.repository.QuestionRepository
import com.example.examenpoo2.ui.service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestionListState())
    val uiState: StateFlow<QuestionListState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            // Indicamos el estado de carga antes de la llamada
            _uiState.update { it.copy(isLoading = true, error = null) }

            // Llamamos a la función suspend directamente
            val result = repository.getQuestions()
            
            when (result) {
                is ApiResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(isLoading = false, questions = result.data, error = null) 
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update { 
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}
