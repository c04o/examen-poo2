package com.example.examenpoo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.examenpoo2.ui.Navigation.AppNavigation
import com.example.examenpoo2.ui.Theme.ExamenPOO2Theme

/**
 * MainActivity: Punto de entrada principal de la aplicación Android.
 * Se encarga de inicializar el entorno de Compose y configurar la navegación base.
 */
class MainActivity : ComponentActivity() {
    
    /**
     * Se llama al crear la actividad. Aquí se establece el contenido visual
     * y se aplican las configuraciones de borde a borde (edge-to-edge).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Habilita que el contenido de la app se extienda debajo de las barras de sistema (estado y navegación).
        enableEdgeToEdge()
        
        setContent {
            // Aplicamos el tema personalizado definido en ui.Theme
            ExamenPOO2Theme {
                // Surface es el contenedor base que proporciona el color de fondo del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llamada al orquestador de navegación que gestiona las pantallas del test
                    AppNavigation()
                }
            }
        }
    }
}
