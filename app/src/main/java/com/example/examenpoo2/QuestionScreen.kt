package com.example.examenpoo2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenpoo2.models.AnswerOption
import com.example.examenpoo2.models.Question

@Composable
fun QuestionScreen(onTestFinished: (Int, Int, Int) -> Unit) {
    val questionBank = listOf(
        Question(
            id = 1,
            text = "¿Qué actividad prefieres realizar en tu tiempo libre?",
            options = listOf(
                AnswerOption("Armar y desarmar equipos electrónicos", 3, 0, 0),
                AnswerOption("Pintar, dibujar o diseñar", 0, 3, 0),
                AnswerOption("Leer sobre anatomía o biología", 0, 0, 3)
            )
        ),
        Question(
            id = 2,
            text = "¿Qué tipo de problemas te gusta resolver?",
            options = listOf(
                AnswerOption("Problemas técnicos o de lógica", 3, 0, 0),
                AnswerOption("Problemas creativos o de diseño", 0, 3, 0),
                AnswerOption("Problemas relacionados con personas o salud", 0, 0, 3)
            )
        ),
        Question(
            id = 3,
            text = "¿Qué materia te atrae más?",
            options = listOf(
                AnswerOption("Matemática o física", 3, 0, 0),
                AnswerOption("Arte o literatura", 0, 3, 0),
                AnswerOption("Biología o química", 0, 0, 3)
            )
        ),
        Question(
            id = 4,
            text = "¿Cómo te describen tus amigos?",
            options = listOf(
                AnswerOption("Analítico y lógico", 3, 0, 0),
                AnswerOption("Creativo e imaginativo", 0, 3, 0),
                AnswerOption("Empático y atento", 0, 0, 3)
            )
        )
    )

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var scoreEngineering by remember { mutableIntStateOf(0) }
    var scoreArts by remember { mutableIntStateOf(0) }
    var scoreHealth by remember { mutableIntStateOf(0) }

    val currentQuestion = questionBank[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Pregunta ${currentQuestion.id}", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(currentQuestion.text, fontSize = 22.sp)

        Spacer(modifier = Modifier.height(32.dp))

        currentQuestion.options.forEach { option ->
            Button(
                onClick = {
                    // Acumulamos los puntos seleccionados
                    scoreEngineering += option.pointsEngineering
                    scoreArts += option.pointsArts
                    scoreHealth += option.pointsHealth

                    // Evaluamos si hay más preguntas
                    if (currentQuestionIndex < questionBank.lastIndex) {
                        currentQuestionIndex++
                    } else {
                        // Al terminar, enviamos los resultados acumulados a la siguiente pantalla
                        onTestFinished(scoreEngineering, scoreArts, scoreHealth)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(option.text)
            }
        }
    }
}