package com.example.examenpoo2.ui.Service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examenpoo2.ui.Model.TestResult
import kotlinx.coroutines.flow.Flow

/**
 * DAO para la entidad [TestResult].
 */
@Dao
interface TestResultDao {
    @Insert
    suspend fun insertResult(result: TestResult)

    @Query("SELECT * FROM test_results WHERE userId = :userId ORDER BY timestamp DESC")
    fun getResultsForUser(userId: Int): Flow<List<TestResult>>
}
