package com.example.examenpoo2.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenpoo2.ui.Repository.UserRepository
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lógica de autenticación y registro de usuarios.
 * Actúa como intermediario entre la vista y el repositorio de datos.
 *
 * @property userRepository El repositorio utilizado para las operaciones de persistencia del usuario.
 */
class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    /**
     * Registra un nuevo usuario en la base de datos local y ejecuta una acción al finalizar.
     *
     * Este método utiliza el [viewModelScope] para realizar la operación de escritura de forma 
     * asíncrona, garantizando que no se bloquee el hilo principal de la interfaz de usuario.
     *
     * @param name Nombre completo del usuario.
     * @param email Correo electrónico de acceso.
     * @param password Contraseña elegida.
     * @param onSuccess Función de callback que se invoca tras completar exitosamente el registro.
     */
    fun registerAndLogin(name: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.registerUser(name, email, password)
            onSuccess()
        }
    }
}
