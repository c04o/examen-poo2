package com.example.examenpoo2.backend.controller

import com.example.examenpoo2.backend.model.ErrorResponse
import com.example.examenpoo2.backend.model.User
import com.example.examenpoo2.backend.model.UserRole
import com.example.examenpoo2.backend.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Users", description = "API para la gestión de usuarios e inicio de sesión")
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @Operation(summary = "Login de usuario", description = "Valida las credenciales y devuelve el perfil del usuario.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Login exitoso", content = [Content(schema = Schema(implementation = User::class))]),
        ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    ])
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: Map<String, String>): ResponseEntity<Any> {
        val email = loginRequest["email"] ?: ""
        val password = loginRequest["password"] ?: ""
        val user = userService.login(email, password)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas")
        }
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Solo permitido para ADMIN.")
    @GetMapping
    fun getAllUsers(@RequestHeader("X-Role", defaultValue = "STUDENT") role: String): ResponseEntity<Any> {
        return if (role.uppercase() == UserRole.ADMIN.name) {
            ResponseEntity.ok(userService.getAllUsers())
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse(403, "Acceso denegado: Solo ADMIN puede ver usuarios.", UserRole.ADMIN))
        }
    }

    @Operation(summary = "Crear nuevo usuario", description = "Solo permitido para ADMIN.")
    @PostMapping
    fun createUser(
        @RequestHeader("X-Role", defaultValue = "STUDENT") role: String,
        @RequestBody user: User
    ): ResponseEntity<Any> {
        return if (role.uppercase() == UserRole.ADMIN.name) {
            val created = userService.createUser(user)
            ResponseEntity.status(HttpStatus.CREATED).body(created)
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse(403, "Acceso denegado: Solo ADMIN puede crear usuarios.", UserRole.ADMIN))
        }
    }

    @Operation(summary = "Eliminar usuario", description = "Solo permitido para ADMIN.")
    @DeleteMapping("/{id}")
    fun deleteUser(
        @RequestHeader("X-Role", defaultValue = "STUDENT") role: String,
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        if (role.uppercase() != UserRole.ADMIN.name) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse(403, "Acceso denegado: Solo ADMIN puede eliminar usuarios.", UserRole.ADMIN))
        }
        return if (userService.deleteUser(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
