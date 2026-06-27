package com.example.examenpoo2.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenpoo2.ui.Repository.QuestionRepository

/**
 * QuestionViewModelFactory: Clase encargada de la creación de instancias de [QuestionViewModel].
 * 
 * Es necesaria para poder inyectar dependencias (como el [QuestionRepository]) en el ViewModel,
 * ya que por defecto el sistema de Android no permite pasar argumentos directamente al constructor
 * de un ViewModel.
 * 
 * @property repository Repositorio de datos que se pasará al [QuestionViewModel].
 */
class QuestionViewModelFactory(private val repository: QuestionRepository) : ViewModelProvider.Factory {

    /**
     * Crea una nueva instancia del ViewModel solicitado si coincide con el tipo esperado.
     * 
     * @param modelClass La clase del ViewModel que se intenta instanciar.
     * @return Una instancia del ViewModel solicitado de tipo [T].
     * @throws IllegalArgumentException Si la clase del ViewModel no es reconocida.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestionViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida: ${modelClass.name}")
    }
}
