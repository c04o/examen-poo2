package com.example.examenpoo2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bienvenido") {
        composable("bienvenido") {
            WelcomeScreen(
                onStartClick = { navController.navigate("pregunta") }
            )
        }
        composable("pregunta") {
            QuestionScreen(
                onOptionSelected = {
                    // Pendiente: Lógica para pasar a la siguiente pregunta o resultados
                }
            )
        }
    }
}