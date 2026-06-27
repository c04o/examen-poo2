package com.example.examenpoo2.ui.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenpoo2.ui.ViewModel.QuestionViewModel
import com.example.examenpoo2.ui.ViewModel.QuestionListState
import com.example.examenpoo2.ui.Model.AnswerOption

/**
 * QuestionListScreen: Pantalla principal que renderiza el listado de preguntas del test.
 *
 * Esta pantalla es reactiva y se redibuja automáticamente basándose en el estado
 * expuesto por el [QuestionViewModel]. Implementa manejo de errores y estados de carga.
 *
 * @param viewModel El ViewModel que suministra los datos y gestiona la lógica de esta pantalla.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionListScreen(viewModel: QuestionViewModel) {
    // Observamos el StateFlow del ViewModel y lo convertimos a un Estado de Compose.
    val uiState by viewModel.uiState.collectAsState()

    // Gradiente de fondo estilizado para una experiencia de usuario inmersiva.
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Cuestionario Vocacional", fontWeight = FontWeight.Bold, color = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0F2027)
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(backgroundGradient)
        ) {
            when {
                // ESTADO 1: Cargando - Se muestra un indicador de progreso circular.
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF00C9FF))
                    }
                }

                // ESTADO 2: Error - Se muestra el mensaje de error y un botón de reintento.
                uiState.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Error de conexión", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(uiState.error ?: "Error desconocido", color = Color.LightGray, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.loadQuestions() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C9FF))
                        ) {
                            Text("Reintentar")
                        }
                    }
                }

                // ESTADO 3: Éxito - Se renderiza la lista de preguntas obtenida de la API.
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.questions) { question ->
                            // Cada pregunta se envuelve en una Card para mejorar la legibilidad.
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                            ) {
                                Column(modifier = Modifier.padding(20.dp)) {
                                    Text(
                                        text = question.text,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFF0F2027)
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    // MANEJO DE OPCIONES:
                                    // Se asegura que si la API no devuelve opciones, se muestren valores de prueba.
                                    val safeOptions = if (question.options.isNullOrEmpty()) {
                                        listOf(
                                            AnswerOption(1, "Totalmente de acuerdo", "A"),
                                            AnswerOption(2, "Neutral", "B"),
                                            AnswerOption(3, "En desacuerdo", "C")
                                        )
                                    } else {
                                        question.options
                                    }

                                    // Renderizado dinámico de los botones de respuesta.
                                    safeOptions.forEach { option ->
                                        OutlinedButton(
                                            onClick = { /* TODO: Implementar lógica de respuesta y guardado */ },
                                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                            shape = RoundedCornerShape(12.dp)
                                        ) {
                                            Text(
                                                text = option.text,
                                                textAlign = TextAlign.Start,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}