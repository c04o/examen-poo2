package com.example.examenpoo2

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenpoo2.models.AnswerOption
import com.example.examenpoo2.models.Question

@Composable
fun QuestionScreen(onTestFinished: (Int, Int, Int) -> Unit) {
    // Mantenemos tu banco de preguntas original
    val questionBank = listOf(
        Question(1, "¿Qué actividad prefieres realizar en tu tiempo libre?", listOf(
            AnswerOption("Armar y desarmar equipos electrónicos", 3, 0, 0),
            AnswerOption("Pintar, dibujar o diseñar", 0, 3, 0),
            AnswerOption("Leer sobre anatomía o biología", 0, 0, 3)
        )),
        Question(2, "¿Qué tipo de problemas te gusta resolver?", listOf(
            AnswerOption("Problemas técnicos o de lógica", 3, 0, 0),
            AnswerOption("Problemas creativos o de diseño", 0, 3, 0),
            AnswerOption("Problemas relacionados con personas o salud", 0, 0, 3)
        )),
        Question(3, "¿Qué materia te atrae más?", listOf(
            AnswerOption("Matemática o física", 3, 0, 0),
            AnswerOption("Arte o literatura", 0, 3, 0),
            AnswerOption("Biología o química", 0, 0, 3)
        )),
        Question(4, "¿Cómo te describen tus amigos?", listOf(
            AnswerOption("Analítico y lógico", 3, 0, 0),
            AnswerOption("Creativo e imaginativo", 0, 3, 0),
            AnswerOption("Empático y atento", 0, 0, 3)
        ))
    )

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var scoreEngineering by remember { mutableIntStateOf(0) }
    var scoreArts by remember { mutableIntStateOf(0) }
    var scoreHealth by remember { mutableIntStateOf(0) }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Barra de progreso personalizada
        val progress = (currentQuestionIndex + 1).toFloat() / questionBank.size
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(50)),
            color = Color(0xFF00C9FF),
            trackColor = Color.White.copy(alpha = 0.2f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Pregunta ${currentQuestionIndex + 1} de ${questionBank.size}",
            color = Color(0xFF92FE9D),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Transición suave entre preguntas
        Crossfade(
            targetState = questionBank[currentQuestionIndex],
            animationSpec = tween(durationMillis = 500),
            label = "question_fade"
        ) { question ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = question.text,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                question.options.forEach { option ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                scoreEngineering += option.pointsEngineering
                                scoreArts += option.pointsArts
                                scoreHealth += option.pointsHealth

                                if (currentQuestionIndex < questionBank.lastIndex) {
                                    currentQuestionIndex++
                                } else {
                                    onTestFinished(scoreEngineering, scoreArts, scoreHealth)
                                }
                            },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color(0xFF00C9FF)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = option.text,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}