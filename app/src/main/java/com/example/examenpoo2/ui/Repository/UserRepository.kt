package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.User
import com.example.examenpoo2.ui.Service.UserDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de gestionar las operaciones de datos relacionadas con los usuarios.
 * Actúa como una capa de abstracción entre el origen de datos ([UserDao]) y la lógica de negocio.
 *
 * @property userDao El DAO de Room para acceder a la base de datos de usuarios.
 */
class UserRepository(private val userDao: UserDao) {

    /**
     * Un [Flow] que emite el último usuario registrado o autenticado en la base de datos local.
     * Permite observar cambios en la sesión del usuario en tiempo real.
     */
    val lastUser: Flow<User?> = userDao.getLastUser()

    /**
     * Registra un nuevo usuario en la base de datos local.
     *
     * @param name Nombre completo del usuario.
     * @param email Correo electrónico único para el registro.
     * @param password Contraseña elegida por el usuario.
     */
    suspend fun registerUser(name: String, email: String, password: String) {
        val user = User(name = name, email = email, password = password)
        userDao.insertUser(user)
    }

    /**
     * Busca un usuario por su dirección de correo electrónico para propósitos de autenticación.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El objeto [User] encontrado o null si no existe.
     */
    suspend fun login(email: String): User? {
        return userDao.getUserByEmail(email)
    }
}
