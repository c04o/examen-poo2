package com.example.examenpoo2.ui.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa a un usuario en el sistema.
 * Almacena la información básica necesaria para el registro y la vinculación de resultados de tests.
 *
 * @property id Identificador único del usuario (Generado automáticamente por Room).
 * @property name Nombre completo del usuario.
 * @property email Correo electrónico único utilizado como identificador de acceso.
 * @property password Contraseña almacenada para la autenticación local.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val role: String = "STUDENT"
)
