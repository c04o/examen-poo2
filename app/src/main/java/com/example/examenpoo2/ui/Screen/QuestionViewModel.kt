package com.example.examenpoo2.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.Repository.QuestionRepository
import com.example.examenpoo2.ui.Repository.UserRepository
import com.example.examenpoo2.ui.Repository.TestResultRepository
import com.example.examenpoo2.ui.Service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val repository: QuestionRepository,
    private val userRepository: UserRepository,
    private val testResultRepository: TestResultRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestionListState())
    val uiState: StateFlow<QuestionListState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            repository.getQuestions().collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is ApiResult.Success -> {
                        _uiState.update { it.copy(isLoading = false, questions = result.data, error = null) }
                    }
                    is ApiResult.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = result.message) }
                    }
                }
            }
        }
    }

    /**
     * Guarda el resultado del test para el usuario actual.
     */
    // ... (dentro de la clase QuestionViewModel)
    fun saveTestResult(eng: Int, art: Int, health: Int, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                // Cambiamos .firstOrNull() por .first { it != null }
                // Esto obliga a la corrutina a esperar hasta que el usuario esté disponible en el Flow
                userRepository.lastUser.first { it != null }?.let { user ->
                    testResultRepository.saveResult(user.id, eng, art, health)
                }
            } catch (e: Exception) {
                Log.e("QuestionViewModel", "Error al guardar: ${e.message}")
            } finally {
                onComplete() // Navegamos a la pantalla de resultados pase lo que pase
            }
        }
    }
}
