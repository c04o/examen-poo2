package com.example.examenpoo2.ui.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Entidad que representa el resultado de un test vocacional realizado por un usuario.
 */
@Entity(tableName = "test_results")
data class TestResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val engineeringScore: Int,
    val artsScore: Int,
    val healthScore: Int,
    val timestamp: Long = System.currentTimeMillis()
)
