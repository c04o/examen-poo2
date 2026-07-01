package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.AnswerOption
import com.example.examenpoo2.ui.Model.Question
import com.example.examenpoo2.ui.Model.Score

/**
 * Proveedor local de preguntas para el test vocacional.
 * Actúa como una fuente de datos de respaldo en caso de que el servicio remoto no esté disponible.
 */
object LocalQuestionProvider {

    /**
     * Retorna una lista predefinida de preguntas diseñadas para evaluar
     * intereses en Ingeniería, Artes y Salud.
     */
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                id = 1,
                text = "¿Qué actividad prefieres realizar en tu tiempo libre?",
                options = listOf(
                    createOption("Armar o reparar dispositivos electrónicos", 10, 0, 0),
                    createOption("Dibujar, pintar o escribir historias", 0, 10, 0),
                    createOption("Aprender sobre primeros auxilios o el cuerpo humano", 0, 0, 10)
                )
            ),
            Question(
                id = 2,
                text = "Si pudieras resolver un problema mundial, ¿cuál elegirías?",
                options = listOf(
                    createOption("Optimizar el consumo de energía con tecnología", 10, 0, 0),
                    createOption("Promover la expresión cultural y artística", 0, 10, 0),
                    createOption("Encontrar la cura para enfermedades complejas", 0, 0, 10)
                )
            ),
            Question(
                id = 3,
                text = "¿Qué tipo de ambiente de trabajo te resulta más atractivo?",
                options = listOf(
                    createOption("Un laboratorio o centro de cómputo avanzado", 10, 0, 5),
                    createOption("Un estudio creativo o escenario", 0, 10, 0),
                    createOption("Un hospital o clínica ayudando a personas", 0, 0, 10)
                )
            ),
            Question(
                id = 4,
                text = "¿Cuál de estas materias te gustaba más en la escuela?",
                options = listOf(
                    createOption("Matemáticas y Física", 10, 0, 0),
                    createOption("Literatura y Arte", 0, 10, 0),
                    createOption("Biología y Química", 0, 2, 10)
                )
            ),
            Question(
                id = 5,
                text = "Al enfrentar un reto difícil, tú:",
                options = listOf(
                    createOption("Analizas los datos y buscas una solución lógica", 10, 0, 0),
                    createOption("Buscas una forma creativa y original de abordarlo", 0, 10, 0),
                    createOption("Piensas en cómo afectará el bienestar de los demás", 0, 0, 10)
                )
            ),
            Question(
                id = 6,
                text = "¿Qué tipo de proyectos te gustaría liderar?",
                options = listOf(
                    createOption("Crear una aplicación o software innovador", 10, 2, 0),
                    createOption("Diseñar una campaña publicitaria o marca", 0, 10, 0),
                    createOption("Coordinar un programa de salud comunitaria", 0, 0, 10)
                )
            ),
            Question(
                id = 7,
                text = "¿Qué te genera más curiosidad?",
                options = listOf(
                    createOption("Cómo funcionan las máquinas por dentro", 10, 0, 0),
                    createOption("El significado detrás de una obra de arte", 0, 10, 0),
                    createOption("Los procesos que mantienen vivo el cuerpo", 0, 0, 10)
                )
            ),
            Question(
                id = 8,
                text = "En un equipo de trabajo, ¿cuál es tu rol ideal?",
                options = listOf(
                    createOption("El que planifica la estructura y el funcionamiento", 10, 0, 0),
                    createOption("El que aporta las ideas visuales y creativas", 0, 10, 0),
                    createOption("El que se asegura del bienestar de las personas", 0, 0, 10)
                )
            ),
            Question(
                id = 9,
                text = "¿Cómo prefieres aprender algo nuevo?",
                options = listOf(
                    createOption("Experimentando con herramientas o código", 10, 0, 0),
                    createOption("Observando y analizando estilos o técnicas", 0, 10, 0),
                    createOption("Leyendo sobre casos reales y experiencias humanas", 0, 0, 10)
                )
            )
        )
    }

    /**
     * Helper para crear una opción de respuesta con sus respectivos puntajes.
     */
    private fun createOption(text: String, engineering: Int, arts: Int, health: Int): AnswerOption {
        return AnswerOption(
            text = text,
            scores = listOf(
                Score("Ingeniería", engineering),
                Score("Artes", arts),
                Score("Salud", health)
            )
        )
    }
}
