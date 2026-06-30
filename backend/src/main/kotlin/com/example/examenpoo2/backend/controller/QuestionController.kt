package com.example.examenpoo2.backend.controller

import com.example.examenpoo2.backend.model.ErrorResponse
import com.example.examenpoo2.backend.model.Question
import com.example.examenpoo2.backend.model.UserProfile
import com.example.examenpoo2.backend.model.UserRole
import com.example.examenpoo2.backend.service.QuestionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Questions", description = "Vocational orientation test questions API")
@RestController
@RequestMapping("/questions")
class QuestionController(private val questionService: QuestionService) {

    @Operation(
        summary = "Check role permissions",
        description = "Returns the profile and permissions for the current role."
    )
    @GetMapping("/me")
    fun checkMyPermissions(@RequestHeader("X-Role", defaultValue = "STUDENT") role: String): UserProfile {
        val userRole = try { UserRole.valueOf(role.uppercase()) } catch (e: Exception) { UserRole.STUDENT }
        
        return when (userRole) {
            UserRole.ADMIN -> UserProfile(userRole, listOf("GET", "POST", "PUT", "DELETE"), "Acceso total al sistema.")
            UserRole.EDITOR -> UserProfile(userRole, listOf("GET", "POST"), "Puede ver y crear preguntas.")
            UserRole.STUDENT -> UserProfile(userRole, listOf("GET"), "Solo puede realizar el test.")
        }
    }

    @Operation(
        summary = "Get all vocational test questions",
        description = "Public access for all roles (ADMIN, EDITOR, STUDENT)."
    )
    @GetMapping
    fun getQuestions(): List<Question> {
        return questionService.getVocationalTest()
    }

    @Operation(
        summary = "Create a new question",
        description = "Allowed for ADMIN and EDITOR roles."
    )
    @PostMapping
    fun createQuestion(
        @RequestHeader("X-Role", defaultValue = "STUDENT") role: String,
        @RequestBody question: Question
    ): ResponseEntity<Any> {
        return if (role.uppercase() == UserRole.ADMIN.name || role.uppercase() == UserRole.EDITOR.name) {
            val created = questionService.createQuestion(question)
            ResponseEntity.status(HttpStatus.CREATED).body(created)
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse(403, "Acceso denegado: Se requiere rol ADMIN o EDITOR.", UserRole.EDITOR))
        }
    }

    @Operation(
        summary = "Update an existing question",
        description = "Allowed only for ADMIN role."
    )
    @PutMapping("/{id}")
    fun updateQuestion(
        @RequestHeader("X-Role", defaultValue = "STUDENT") role: String,
        @PathVariable id: Int,
        @RequestBody question: Question
    ): ResponseEntity<Any> {
        if (role.uppercase() != UserRole.ADMIN.name) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse(403, "Acceso denegado: Solo el ADMIN puede actualizar.", UserRole.ADMIN))
        }
        val updated = questionService.updateQuestion(id, question)
        return if (updated != null) {
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(
        summary = "Delete a question",
        description = "Allowed only for ADMIN role."
    )
    @DeleteMapping("/{id}")
    fun deleteQuestion(
        @RequestHeader("X-Role", defaultValue = "STUDENT") role: String,
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        if (role.uppercase() != UserRole.ADMIN.name) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse(403, "Acceso denegado: Solo el ADMIN puede eliminar.", UserRole.ADMIN))
        }
        return if (questionService.deleteQuestion(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
