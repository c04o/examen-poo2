package com.example.examenpoo2.backend.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserRole", description = "Roles permitidos en el sistema")
enum class UserRole {
    ADMIN,
    EDITOR,
    STUDENT
}
