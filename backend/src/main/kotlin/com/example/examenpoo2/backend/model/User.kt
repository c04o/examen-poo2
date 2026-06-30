package com.example.examenpoo2.backend.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "User", description = "Modelo de usuario del sistema")
data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String, // En un entorno real, esto iría hasheado
    val role: UserRole
)
