package com.example.examenpoo2.backend.service

import com.example.examenpoo2.backend.model.Question
import com.example.examenpoo2.backend.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(private val questionRepository: QuestionRepository) {
    fun getVocationalTest(): List<Question> = questionRepository.getAllQuestions()

    fun createQuestion(question: Question): Question = questionRepository.addQuestion(question)

    fun updateQuestion(id: Int, question: Question): Question? = questionRepository.updateQuestion(id, question)

    fun deleteQuestion(id: Int): Boolean = questionRepository.deleteQuestion(id)
}
