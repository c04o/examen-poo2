package com.example.examenpoo2.backend.repository

import com.example.examenpoo2.backend.model.Question
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository

interface QuestionRepository {
    fun getAllQuestions(): List<Question>
}

@Repository
class JsonQuestionRepository : QuestionRepository {
    private val mapper = jacksonObjectMapper()

    override fun getAllQuestions(): List<Question> {
        val resource = ClassPathResource("questions.json")
        return mapper.readValue(resource.inputStream)
    }
}
