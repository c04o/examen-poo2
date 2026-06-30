package com.example.examenpoo2.backend.model

data class UserProfile(
    val role: UserRole,
    val permissions: List<String>,
    val description: String
)
