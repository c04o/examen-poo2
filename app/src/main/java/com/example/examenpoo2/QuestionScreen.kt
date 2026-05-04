package com.example.examenpoo2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenpoo2.models.AnswerOption
import com.example.examenpoo2.models.Question

@Composable
fun QuestionScreen(onOptionSelected: () -> Unit) {
    // Instanciación de objetos (POO) con datos de prueba temporal
    val mockQuestion = Question(
        id = 1,
        text = "¿Qué actividad prefieres realizar en tu tiempo libre?",
        options = listOf(
            AnswerOption("Armar y desarmar equipos electrónicos", 3, 0, 0),
            AnswerOption("Pintar, dibujar o diseñar", 0, 3, 0),
            AnswerOption("Leer sobre anatomía o biología", 0, 0, 3)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Pregunta ${mockQuestion.id}",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = mockQuestion.text,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Iteración sobre la lista de opciones para generar componentes interactivos
        mockQuestion.options.forEach { option ->
            Button(
                onClick = onOptionSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = option.text)
            }
        }
    }
}