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
 * ViewModel encargado de gestionar la lógica de la pantalla de lista de preguntas.
 * Se comunica con el [QuestionRepository] para obtener los datos y expone el estado
 * de la UI mediante [StateFlow].
 */
class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {

    // Estado interno mutable encapsulado para evitar modificaciones externas
    private val _uiState = MutableStateFlow(QuestionListState())
    
    /**
     * Estado de la UI expuesto como flujo inmutable para que la vista lo observe.
     * Contiene la lista de preguntas, estado de carga y posibles errores.
     */
    val uiState: StateFlow<QuestionListState> = _uiState.asStateFlow()

    init {
        // Carga inicial de preguntas al crear el ViewModel
        loadQuestions()
    }

    /**
     * Inicia la petición al repositorio para obtener las preguntas del test.
     * Utiliza [viewModelScope] para que la corrutina se cancele automáticamente
     * si el ViewModel es destruido.
     */
    fun loadQuestions() {
        viewModelScope.launch {
            repository.getQuestions().collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        // Actualiza el estado indicando que la carga está en proceso
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is ApiResult.Success -> {
                        // Actualiza el estado con la lista de preguntas recibida
                        _uiState.update { 
                            it.copy(
                                isLoading = false, 
                                questions = result.data, 
                                error = null
                            ) 
                        }
                    }
                    is ApiResult.Error -> {
                        // Actualiza el estado con el mensaje de error correspondiente
                        _uiState.update { 
                            it.copy(
                                isLoading = false, 
                                error = result.message 
                            ) 
                        }
                    }
                }
            }
        }
    }
}
