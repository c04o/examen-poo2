package com.example.examenpoo2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
    val careerName = result.getRecommendedCareer()

    // Determinamos el ícono basado en el string que retorna tu lógica
    val resultIcon = when {
        careerName.contains("Ingeniería") -> Icons.Default.Build
        careerName.contains("Diseño") -> Icons.Default.Create
        else -> Icons.Default.Favorite
    }

    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF11998E), Color(0xFF38EF7D))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(800)) + slideInVertically(tween(800)) { -100 }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    shape = RoundedCornerShape(100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.size(120.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = resultIcon,
                            contentDescription = "Icono de Carrera",
                            tint = Color(0xFF11998E),
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "¡Este es tu camino!",
                    fontSize = 20.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = careerName,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = onRestart,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(alpha = 0.3f),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(56.dp)
                ) {
                    Text("Volver a intentar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}