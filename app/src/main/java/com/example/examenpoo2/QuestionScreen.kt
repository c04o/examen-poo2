package com.example.examenpoo2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenpoo2.ui.Service.ServiceLocator
import com.example.examenpoo2.ui.screen.QuestionViewModel
import com.example.examenpoo2.ui.screen.QuestionViewModelFactory

@Composable
fun QuestionScreen(
    onTestFinished: (Int, Int, Int) -> Unit,
    viewModel: QuestionViewModel = viewModel(
        factory = QuestionViewModelFactory(
            ServiceLocator.questionRepository,
            ServiceLocator.userRepository,
            ServiceLocator.testResultRepository
        )
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF00C9FF))
        }
        return
    }

    if (uiState.error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Error al cargar preguntas", color = Color.Red, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(uiState.error!!, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.loadQuestions() }) {
                    Text("Reintentar")
                }
            }
        }
        return
    }

    val questionBank = uiState.questions
    if (questionBank.isNotEmpty()) {
        var currentQuestionIndex by remember { mutableStateOf(0) }
        var scoreEngineering by remember { mutableStateOf(0) }
        var scoreArts by remember { mutableStateOf(0) }
        var scoreHealth by remember { mutableStateOf(0) }

        val currentQuestion = questionBank[currentQuestionIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pregunta ${currentQuestionIndex + 1} de ${questionBank.size}",
                color = Color.White,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = currentQuestion.text,
                color = Color.White,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            currentQuestion.options.forEach { option ->
                Card(
                    onClick = {
                        scoreEngineering += option.pointsEngineering
                        scoreArts += option.pointsArts
                        scoreHealth += option.pointsHealth

                        if (currentQuestionIndex < questionBank.lastIndex) {
                            currentQuestionIndex++
                        } else {
                            // LLAMADA CRÍTICA: Guardar en historial antes de navegar a resultados
                            viewModel.saveTestResult(scoreEngineering, scoreArts, scoreHealth) {
                                onTestFinished(scoreEngineering, scoreArts, scoreHealth)
                            }
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
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
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay preguntas disponibles en el servidor.", color = Color.White)
        }
    }
}
