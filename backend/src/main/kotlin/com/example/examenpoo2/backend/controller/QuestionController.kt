package com.example.examenpoo2.backend.controller

import com.example.examenpoo2.backend.model.Question
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
        summary = "Get all vocational test questions",
        description = "Returns the list of questions with multiple-choice options."
    )
    @GetMapping
    fun getQuestions(): List<Question> {
        return questionService.getVocationalTest()
    }

    @Operation(
        summary = "Create a new question",
        description = "Adds a new question to the test bank."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createQuestion(@RequestBody question: Question): Question {
        return questionService.createQuestion(question)
    }

    @Operation(
        summary = "Update an existing question",
        description = "Updates the text or options of a question by its ID."
    )
    @PutMapping("/{id}")
    fun updateQuestion(
        @PathVariable id: Int,
        @RequestBody question: Question
    ): ResponseEntity<Question> {
        val updated = questionService.updateQuestion(id, question)
        return if (updated != null) {
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(
        summary = "Delete a question",
        description = "Removes a question from the test bank by its ID."
    )
    @DeleteMapping("/{id}")
    fun deleteQuestion(@PathVariable id: Int): ResponseEntity<Void> {
        return if (questionService.deleteQuestion(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
