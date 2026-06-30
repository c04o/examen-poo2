package com.example.examenpoo2.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenpoo2.ui.Repository.TestResultRepository
import com.example.examenpoo2.ui.Repository.UserRepository

/**
 * Fábrica personalizada para la creación de instancias de [HistoryViewModel].
 *
 * Es necesaria para permitir la Inyección de Dependencias manual de los repositorios
 * en el constructor del ViewModel, ya que la fábrica por defecto de Android no soporta
 * parámetros personalizados.
 *
 * @param userRepository Repositorio de usuarios que se inyectará en el ViewModel.
 * @param testResultRepository Repositorio de resultados que se inyectará en el ViewModel.
 */
class HistoryViewModelFactory(
    private val userRepository: UserRepository,
    private val testResultRepository: TestResultRepository
) : ViewModelProvider.Factory {

    /**
     * Crea una nueva instancia del ViewModel solicitado.
     *
     * @param modelClass La clase del ViewModel a crear.
     * @return Una instancia de [HistoryViewModel] con sus dependencias inyectadas.
     * @throws IllegalArgumentException Si la clase solicitada no es [HistoryViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(userRepository, testResultRepository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}
