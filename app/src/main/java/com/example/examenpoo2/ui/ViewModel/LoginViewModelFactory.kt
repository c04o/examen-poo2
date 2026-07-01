package com.example.examenpoo2.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenpoo2.ui.Repository.UserRepository

/**
 * Fábrica personalizada para la creación de instancias de [LoginViewModel].
 * 
 * Permite inyectar el [UserRepository] en el constructor del ViewModel, facilitando
 * la separación de responsabilidades y la testabilidad.
 *
 * @property userRepository Repositorio de usuarios que se pasará al ViewModel.
 */
class LoginViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    /**
     * Crea una nueva instancia del ViewModel solicitado.
     *
     * @param modelClass La clase del ViewModel que se desea instanciar.
     * @return Una instancia de [LoginViewModel] con su dependencia inyectada.
     * @throws IllegalArgumentException Si la clase proporcionada no es [LoginViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida: ${modelClass.name}")
    }
}
