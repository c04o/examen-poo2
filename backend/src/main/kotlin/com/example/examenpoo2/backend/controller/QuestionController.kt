package com.example.examenpoo2.backend.controller

import com.example.examenpoo2.backend.model.Question
import com.example.examenpoo2.backend.service.QuestionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Questions", description = "Vocational orientation test questions API")
@RestController
@RequestMapping("/questions")
class QuestionController(private val questionService: QuestionService) {

    @Operation(
        summary = "Get all vocational test questions",
        description = "Returns the list of questions with multiple-choice options. Each option carries points for Engineering, Arts, and Health vocational areas."
    )
    @GetMapping
    fun getQuestions(): List<Question> {
        return questionService.getVocationalTest()
    }
}
