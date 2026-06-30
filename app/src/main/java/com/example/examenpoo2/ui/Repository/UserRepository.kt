package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.User
import com.example.examenpoo2.ui.Service.RetrofitClient
import com.example.examenpoo2.ui.Service.UserDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de gestionar las operaciones de datos relacionadas con los usuarios.
 * Ahora sincronizado con el Backend para validación real.
 */
class UserRepository(private val userDao: UserDao) {

    val lastUser: Flow<User?> = userDao.getLastUser()

    /**
     * Intenta iniciar sesión contra el Backend.
     * Si es exitoso, guarda o actualiza el usuario en la base de datos local (Room).
     */
    suspend fun loginWithBackend(email: String, password: String): User? {
        return try {
            val response = RetrofitClient.userService.login(mapOf(
                "email" to email,
                "password" to password
            ))
            
            if (response.isSuccessful && response.body() != null) {
                val user = response.body()!!
                // Guardamos en Room para persistencia local y manejo de sesión
                userDao.insertUser(user)
                user
            } else {
                null
            }
        } catch (e: Exception) {
            // Si el backend falla, intentamos login local como fallback (opcional)
            userDao.getUserByEmail(email)?.takeIf { it.password == password }
        }
    }

    suspend fun registerUser(name: String, email: String, password: String) {
        val user = User(name = name, email = email, password = password)
        userDao.insertUser(user)
    }
}
