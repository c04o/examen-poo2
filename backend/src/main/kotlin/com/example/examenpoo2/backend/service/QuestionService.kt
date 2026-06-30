package com.example.examenpoo2.backend.service

import com.example.examenpoo2.backend.model.Question
import com.example.examenpoo2.backend.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(private val questionRepository: QuestionRepository) {
    fun getVocationalTest(): List<Question> {
        return questionRepository.getAllQuestions()
    }
}
