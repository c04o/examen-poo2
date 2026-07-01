package com.example.examenpoo2.backend.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Perfil de usuario con sus permisos")
data class UserProfile(
    val role: UserRole,
    val permissions: List<String>,
    val description: String
)
