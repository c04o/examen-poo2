package com.example.examenpoo2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpoo2.ui.Screen.LockScreen

/**
 * AppNavigation: Gestiona el flujo de navegación de la aplicación.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LockScreen(onLoginSuccess = {
                navController.navigate("bienvenido") { popUpTo("login") { inclusive = true } }
            })
        }
        composable("bienvenido") {
            WelcomeScreen(onStartClick = { navController.navigate("pregunta") })
        }
        composable("pregunta") {
            QuestionScreen(onTestFinished = { eng, art, health ->
                navController.navigate("resultado/$eng/$art/$health")
            })
        }
        // Cuarta Pantalla: Resultados con argumentos
        composable("resultado/{eng}/{art}/{health}") { backStackEntry ->
            val eng = backStackEntry.arguments?.getString("eng")?.toIntOrNull() ?: 0
            val art = backStackEntry.arguments?.getString("art")?.toIntOrNull() ?: 0
            val health = backStackEntry.arguments?.getString("health")?.toIntOrNull() ?: 0

            ResultScreen(eng, art, health, onRestart = {
                navController.navigate("bienvenido") { popUpTo("resultado") { inclusive = true } }
            })
        }
    }
}