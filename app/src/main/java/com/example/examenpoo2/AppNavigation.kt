package com.example.examenpoo2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpoo2.ui.Screen.LockScreen

/**
 * AppNavigation: Gestiona el flujo de navegación de la aplicación.
 * Define las rutas (destinos) y la pantalla inicial.
 */
@Composable
fun AppNavigation() {
    // NavController: Objeto que gestiona la navegación entre pantallas.
    val navController = rememberNavController()

    // NavHost: Define el grafo de navegación y los destinos posibles.
    NavHost(navController = navController, startDestination = "login") {
        
        // Ruta para la pantalla de inicio de sesión.
        composable("login") {
            LockScreen(
                onLoginSuccess = { 
                    // Al tener éxito, navegamos a 'bienvenido' y limpiamos el historial.
                    navController.navigate("bienvenido") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        
        // Ruta para la pantalla de bienvenida.
        composable("bienvenido") {
            WelcomeScreen(
                onStartClick = { navController.navigate("pregunta") }
            )
        }
        
        // Ruta para la pantalla de preguntas del test.
        composable("pregunta") {
            // Pantalla de preguntas del test (Pendiente de implementación completa)
            Text(text = "Aquí comenzará el Test Vocacional")
        }
    }
}
