package com.example.examenpoo2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenpoo2.ui.Screen.LockScreen
import com.example.examenpoo2.ui.Screen.WelcomeScreen
import com.example.examenpoo2.ui.Screen.QuestionListScreen
import com.example.examenpoo2.ui.Service.ServiceLocator
import com.example.examenpoo2.ui.ViewModel.QuestionViewModel
import com.example.examenpoo2.ui.ViewModel.QuestionViewModelFactory

// Importamos las rutas explícitamente si es necesario o usamos el mismo paquete
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginRoute) {
        
        composable<LoginRoute> {
            LockScreen(
                onLoginSuccess = { 
                    navController.navigate(WelcomeRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                }
            )
        }
        
        composable<WelcomeRoute> {
            WelcomeScreen(
                onStartClick = { navController.navigate(QuestionListRoute) }
            )
        }
        
        composable<QuestionListRoute> {
            val viewModel: QuestionViewModel = viewModel(
                factory = QuestionViewModelFactory(ServiceLocator.questionRepository)
            )
            
            QuestionListScreen(viewModel = viewModel)
        }
    }
}
