package com.example.examenpoo2.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.Model.TestResult
import com.example.examenpoo2.ui.Repository.TestResultRepository
import com.example.examenpoo2.ui.Repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
/**
 * ViewModel encargado de gestionar y exponer el historial de resultados del usuario actual.
 *
 * Implementa una arquitectura reactiva donde el historial se actualiza automáticamente
 * en respuesta al cambio del usuario logueado en el sistema.
 *
 * @property userRepository Repositorio para acceder a la información del usuario activo.
 * @property testResultRepository Repositorio para gestionar las operaciones de persistencia de resultados.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class HistoryViewModel(
    private val userRepository: UserRepository,
    private val testResultRepository: TestResultRepository
) : ViewModel() {
    /**
     * ViewModel encargado de gestionar y exponer el historial de resultados del usuario actual.
     *
     * Implementa una arquitectura reactiva donde el historial se actualiza automáticamente
     * en respuesta al cambio del usuario logueado en el sistema.
     *
     * @property userRepository Repositorio para acceder a la información del usuario activo.
     * @property testResultRepository Repositorio para gestionar las operaciones de persistencia de resultados.
     */
    val historyState: StateFlow<List<TestResult>> = userRepository.lastUser
        .flatMapLatest { user ->
            if (user != null) {
                testResultRepository.getResultsForUser(user.id)
            } else {
                flowOf(emptyList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
