package com.example.examenpoo2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.examenpoo2.ui.screen.LockScreen
import com.example.examenpoo2.ui.screen.WelcomeScreen
import com.example.examenpoo2.QuestionScreen
import com.example.examenpoo2.ResultScreen
import com.example.examenpoo2.ui.screen.HistoryScreen

/**
 * Función composable que define la estructura de navegación principal de la aplicación.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {

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
                onStartClick = { navController.navigate(QuestionListRoute) },
                onHistoryClick = { navController.navigate(HistoryRoute) }
            )
        }

        composable<HistoryRoute> {
            HistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<QuestionListRoute> {
            QuestionScreen(
                onTestFinished = { results ->
                    navController.navigate(
                        ResultRoute(
                            results = results
                        )
                    ) {
                        popUpTo(WelcomeRoute)
                    }
                }
            )
        }

        composable<ResultRoute> { backStackEntry ->
            val route: ResultRoute = backStackEntry.toRoute()
            ResultScreen(
                scores = route.results,
                onRestart = {
                    navController.navigate(WelcomeRoute) {
                        popUpTo(WelcomeRoute) { inclusive = true }
                    }
                }
            )
        }
    }
}
