package com.example.examenpoo2.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.Repository.UserRepository
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica de autenticación sincronizada con el Backend.
 */
class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    /**
     * Intenta iniciar sesión con el backend.
     */
    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            val user = userRepository.loginWithBackend(email, password)
            
            isLoading = false
            if (user != null) {
                onSuccess()
            } else {
                errorMessage = "Credenciales incorrectas o error de conexión"
            }
        }
    }

    // Mantenemos este para compatibilidad si se desea registro local rápido, 
    // pero la prioridad ahora es el login real.
    fun registerAndLogin(name: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.registerUser(name, email, password)
            onSuccess()
        }
    }
}
