package com.example.examenpoo2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpoo2.ui.Screen.LockScreen
import com.example.examenpoo2.ui.Screen.QuestionListScreen
import com.example.examenpoo2.ui.Screen.WelcomeScreen
import com.example.examenpoo2.ui.Service.ServiceLocator
import com.example.examenpoo2.ui.ViewModel.QuestionViewModel
import com.example.examenpoo2.ui.ViewModel.QuestionViewModelFactory

/**
 * AppNavigation: Orquestador principal de la navegación en la aplicación.
 * Utiliza Jetpack Compose Navigation para gestionar el flujo entre pantallas
 * de forma reactiva y segura en cuanto a tipos (Type-Safe).
 */
@Composable
fun AppNavigation() {
    // NavController: Gestiona el estado de la pila de navegación y permite el movimiento entre destinos.
    val navController = rememberNavController()

    // NavHost: Define el grafo de navegación y asocia cada ruta serializable con un Composable.
    NavHost(navController = navController, startDestination = LoginRoute) {

        /**
         * Destino: Pantalla de Bloqueo (Login).
         * Al tener éxito, navega a la Bienvenida y limpia el historial para evitar volver al login.
         */
        composable<LoginRoute> {
            LockScreen(
                onLoginSuccess = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo<LoginRoute> { inclusive = true }
                    }
                }
            )
        }

        /**
         * Destino: Pantalla de Bienvenida.
         * Punto de inicio informativo antes de comenzar el test real.
         */
        composable<WelcomeRoute> {
            WelcomeScreen(
                onStartClick = { navController.navigate(QuestionListRoute) }
            )
        }

        /**
         * Destino: Pantalla de Cuestionario (Lista de Preguntas).
         * Aquí se inyecta el ViewModel utilizando una Factory para proveer el repositorio
         * centralizado del [ServiceLocator].
         */
        composable<QuestionListRoute> {
            val viewModel: QuestionViewModel = viewModel(
                factory = QuestionViewModelFactory(ServiceLocator.questionRepository)
            )

            QuestionListScreen(viewModel = viewModel)
        }
    }
}
