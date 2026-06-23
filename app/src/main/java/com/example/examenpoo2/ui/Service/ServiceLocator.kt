package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.repository.QuestionRepository
import com.example.examenpoo2.ui.service.QuestionService

object ServiceLocator {
    private val questionService: QuestionService =
        RetrofitClient.instance.create(QuestionService::class.java)

    val questionRepository: QuestionRepository = QuestionRepository(questionService)
}
