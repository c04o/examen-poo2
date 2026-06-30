package com.example.examenpoo2.backend.model

data class ErrorResponse(
    val status: Int,
    val message: String,
    val requiredRole: UserRole
)
