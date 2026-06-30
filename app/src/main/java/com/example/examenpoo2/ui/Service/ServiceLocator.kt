package com.example.examenpoo2.ui.Service

import android.content.Context
import com.example.examenpoo2.ui.Repository.QuestionRepository
import com.example.examenpoo2.ui.Repository.UserRepository
import com.example.examenpoo2.ui.Repository.TestResultRepository

/**
 * ServiceLocator: Centraliza la creación de dependencias para asegurar una única instancia de los servicios.
 */
object ServiceLocator {

    private var database: AppDatabase? = null

    fun init(context: Context) {
        if (database == null) {
            database = AppDatabase.getDatabase(context)
        }
    }

    val questionRepository: QuestionRepository by lazy { 
        QuestionRepository()
    }

    val userRepository: UserRepository by lazy {
        val db = database ?: throw IllegalStateException("ServiceLocator must be initialized with context")
        UserRepository(db.userDao())
    }

    val testResultRepository: TestResultRepository by lazy {
        val db = database ?: throw IllegalStateException("ServiceLocator must be initialized with context")
        TestResultRepository(db.testResultDao())
    }
}
