package com.example.examenpoo2.backend.repository

import com.example.examenpoo2.backend.model.Question
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository

interface QuestionRepository {
    fun getAllQuestions(): List<Question>
    fun addQuestion(question: Question): Question
    fun updateQuestion(id: Int, question: Question): Question?
    fun deleteQuestion(id: Int): Boolean
}

@Repository
class JsonQuestionRepository : QuestionRepository {
    private val mapper = jacksonObjectMapper()
    private val questions: MutableList<Question> by lazy {
        val resource = ClassPathResource("questions.json")
        mapper.readValue<List<Question>>(resource.inputStream).toMutableList()
    }

    override fun getAllQuestions(): List<Question> = questions

    override fun addQuestion(question: Question): Question {
        val newQuestion = if (question.id <= 0) {
            question.copy(id = (questions.maxOfOrNull { it.id } ?: 0) + 1)
        } else {
            question
        }
        questions.add(newQuestion)
        return newQuestion
    }

    override fun updateQuestion(id: Int, question: Question): Question? {
        val index = questions.indexOfFirst { it.id == id }
        return if (index != -1) {
            val updated = question.copy(id = id)
            questions[index] = updated
            updated
        } else {
            null
        }
    }

    override fun deleteQuestion(id: Int): Boolean {
        return questions.removeIf { it.id == id }
    }
}
