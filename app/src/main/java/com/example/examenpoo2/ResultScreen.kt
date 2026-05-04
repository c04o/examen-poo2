package com.example.examenpoo2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Aplicación de POO: Encapsulamiento de la lógica de negocio
class VocationalResult(val eng: Int, val art: Int, val health: Int) {
    fun getRecommendedCareer(): String {
        return when {
            eng >= art && eng >= health -> "Ingeniería en Sistemas o Civil"
            art >= eng && art >= health -> "Diseño Gráfico o Arquitectura"
            else -> "Medicina o Enfermería"
        }
    }
}

@Composable
fun ResultScreen(eng: Int, art: Int, health: Int, onRestart: () -> Unit) {
    val result = VocationalResult(eng, art, health)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tu Perfil Vocacional", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = result.getRecommendedCareer(),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color(0xFF4F46E5)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = onRestart) {
            Text("Repetir Test")
        }
    }
}