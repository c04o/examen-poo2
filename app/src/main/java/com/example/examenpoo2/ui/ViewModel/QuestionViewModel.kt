package com.example.examenpoo2.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.Repository.QuestionRepository
import com.example.examenpoo2.ui.Service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica del cuestionario vocacional.
 *
 * Se encarga de solicitar las preguntas al repositorio y exponer el estado
 * de la interfaz de usuario de forma reactiva mediante [StateFlow] siguiendo el patrón UDF.
 *
 * @property repository El repositorio de donde se obtienen las preguntas para el examen.
 */
class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(QuestionListState())

    /**
     * Estado de la interfaz de usuario que contiene la lista de preguntas,
     * el estado de carga y posibles mensajes de error.
     */
    val uiState: StateFlow<QuestionListState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }

    /**
     * Inicia la carga de preguntas desde el repositorio.
     *
     * Utiliza el [viewModelScope] para recolectar los resultados del flujo del repositorio
     * y actualiza el [_uiState] según el estado de la operación (Loading, Success o Error).
     */
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
}
