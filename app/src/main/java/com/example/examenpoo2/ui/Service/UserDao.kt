package com.example.examenpoo2.ui.Service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examenpoo2.ui.Model.User
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz de Acceso a Datos (DAO) para la entidad [User].
 * Proporciona los métodos para interactuar con la tabla "users" en la base de datos local.
 */
@Dao
interface UserDao {
    /**
     * Inserta un nuevo usuario en la base de datos.
     * Si el usuario ya existe (basado en la clave primaria), se reemplaza.
     *
     * @param user El objeto [User] a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Obtiene el último usuario registrado o autenticado.
     * Utiliza un [Flow] para observar cambios en la base de datos en tiempo real.
     *
     * @return Un [Flow] que emite el último [User] o null si la tabla está vacía.
     */
    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    fun getLastUser(): Flow<User?>

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El objeto [User] correspondiente o null si no se encuentra.
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}
