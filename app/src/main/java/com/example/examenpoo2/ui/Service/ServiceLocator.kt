package com.example.examenpoo2.ui.Service

import com.example.examenpoo2.ui.Repository.QuestionRepository

object ServiceLocator {
    private val questionService by lazy { RetrofitClient.instance }
    
    val questionRepository by lazy {
        QuestionRepository(questionService)
    }
}
