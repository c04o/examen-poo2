package com.example.examenpoo2.backend.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "ErrorResponse", description = "Modelo para respuestas de error de seguridad")
data class ErrorResponse(
    val status: Int,
    val message: String,
    val requiredRole: UserRole
)
