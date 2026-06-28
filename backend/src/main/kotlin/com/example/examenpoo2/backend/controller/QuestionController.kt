package com.example.examenpoo2.backend.controller

import com.example.examenpoo2.backend.model.AnswerOption
import com.example.examenpoo2.backend.model.Question
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Questions", description = "Vocational orientation test questions API")
@RestController
@RequestMapping("/questions")
class QuestionController {

    @Operation(
        summary = "Get all vocational test questions",
        description = "Returns the list of questions with multiple-choice options. Each option carries points for Engineering, Arts, and Health vocational areas."
    )
    @GetMapping
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                id = 1,
                text = "¿Qué actividad prefieres realizar en tu tiempo libre?",
                options = listOf(
                    AnswerOption("Armar o reparar dispositivos electrónicos", pointsEngineering = 3, pointsArts = 0, pointsHealth = 0),
                    AnswerOption("Dibujar, pintar o escribir historias", pointsEngineering = 0, pointsArts = 3, pointsHealth = 0),
                    AnswerOption("Aprender primeros auxilios o leer sobre el cuerpo humano", pointsEngineering = 0, pointsArts = 0, pointsHealth = 3)
                )
            ),
            Question(
                id = 2,
                text = "¿Cuál de estas asignaturas te interesa más?",
                options = listOf(
                    AnswerOption("Matemáticas, Física o Programación", pointsEngineering = 3, pointsArts = 0, pointsHealth = 0),
                    AnswerOption("Literatura, Arte o Música", pointsEngineering = 0, pointsArts = 3, pointsHealth = 0),
                    AnswerOption("Biología, Química o Ciencias de la Salud", pointsEngineering = 0, pointsArts = 0, pointsHealth = 3)
                )
            ),
            Question(
                id = 3,
                text = "Si pudieras resolver un problema en el mundo, ¿cuál elegirías?",
                options = listOf(
                    AnswerOption("Desarrollar software para automatizar procesos", pointsEngineering = 3, pointsArts = 0, pointsHealth = 0),
                    AnswerOption("Diseñar campañas creativas para concientización social", pointsEngineering = 0, pointsArts = 3, pointsHealth = 0),
                    AnswerOption("Curar enfermedades o mejorar el acceso a la salud", pointsEngineering = 0, pointsArts = 0, pointsHealth = 3)
                )
            )
        )
    }
}
