package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.TestResult
import com.example.examenpoo2.ui.Service.TestResultDao
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para gestionar los resultados de los tests.
 */
class TestResultRepository(private val testResultDao: TestResultDao) {

    /**
     * Guarda un nuevo resultado de test.
     */
    suspend fun saveResult(userId: Int, scores: Map<String, Int>) {
        val result = TestResult(
            userId = userId,
            scores = scores
        )
        testResultDao.insertResult(result)
    }

    /**
     * Obtiene el historial de resultados para un usuario específico.
     */
    fun getResultsForUser(userId: Int): Flow<List<TestResult>> {
        return testResultDao.getResultsForUser(userId)
    }
}
