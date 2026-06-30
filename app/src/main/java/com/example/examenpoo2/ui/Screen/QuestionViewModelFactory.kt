package com.example.examenpoo2.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenpoo2.ui.Repository.QuestionRepository
import com.example.examenpoo2.ui.Repository.UserRepository
import com.example.examenpoo2.ui.Repository.TestResultRepository

class QuestionViewModelFactory(
    private val repository: QuestionRepository,
    private val userRepository: UserRepository,
    private val testResultRepository: TestResultRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestionViewModel(repository, userRepository, testResultRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
