package com.example.examenpoo2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenpoo2.ui.Model.TestResult
import com.example.examenpoo2.ui.Service.ServiceLocator
import com.example.examenpoo2.ui.ViewModel.HistoryViewModel
import com.example.examenpoo2.ui.ViewModel.HistoryViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

/**
 * Pantalla que muestra el historial de resultados de los tests vocacionales realizados por el usuario.
 *
 * Esta función composable utiliza un [HistoryViewModel] para observar el estado de los resultados
 * guardados en la base de datos local a través de Room.
 *
 * @param onBack Función de callback que se ejecuta al presionar el botón de retroceso.
 * @param viewModel Instancia del ViewModel encargada de la lógica de negocio del historial.
 *                  Se inicializa por defecto usando [HistoryViewModelFactory].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBack: () -> Unit,
    viewModel: HistoryViewModel = viewModel(
        factory = HistoryViewModelFactory(
            ServiceLocator.userRepository,
            ServiceLocator.testResultRepository
        )
    )
) {
    // Observa el estado del historial desde el ViewModel
    val history by viewModel.historyState.collectAsState()

    // Gradiente de fondo para mantener la coherencia visual con el resto de la app
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF141E30), Color(0xFF243B55))
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Tests", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Volver", 
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
                .padding(padding)
        ) {
            // Muestra un estado vacío si no hay registros en la base de datos
            if (history.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.History,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = Color.White.copy(alpha = 0.3f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "No tienes resultados guardados",
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 18.sp
                    )
                }
            } else {
                // Lista de tarjetas con scroll eficiente
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(history) { result ->
                        HistoryCard(result)
                    }
                }
            }
        }
    }
}
/**
 * Componente visual que representa un registro individual del historial.
 *
 * Muestra la fecha del test y el desglose de puntajes por categoría (Ingeniería, Artes, Salud).
 *
 * @param result Objeto [TestResult] que contiene la información del test a mostrar.
 */
@Composable
fun HistoryCard(result: TestResult) {
    // Formateador de fecha para presentarla de forma legible
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val dateString = sdf.format(Date(result.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = dateString,
                color = Color(0xFF00C9FF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ResultItem("Ingeniería", result.engineeringScore)
                ResultItem("Artes", result.artsScore)
                ResultItem("Salud", result.healthScore)
            }
        }
    }
}

/**
 * Componente interno para mostrar un puntaje específico con su etiqueta.
 *
 * @param label Nombre del área evaluada.
 * @param score Puntaje obtenido en dicha área.
 */
@Composable
fun ResultItem(label: String, score: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
        Text(score.toString(), color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}
