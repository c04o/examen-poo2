package com.example.examenpoo2.ui.Repository

import com.example.examenpoo2.ui.Model.AnswerOption
import com.example.examenpoo2.ui.Model.Question

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
                    AnswerOption("Armar o reparar dispositivos electrónicos", 10, 0, 0),
                    AnswerOption("Dibujar, pintar o escribir historias", 0, 10, 0),
                    AnswerOption("Aprender sobre primeros auxilios o el cuerpo humano", 0, 0, 10),
                    AnswerOption("Organizar eventos grupales", 2, 5, 3)
                )
            ),
            Question(
                id = 2,
                text = "Si pudieras resolver un problema mundial, ¿cuál elegirías?",
                options = listOf(
                    AnswerOption("Optimizar el consumo de energía con tecnología", 10, 0, 0),
                    AnswerOption("Promover la expresión cultural y artística", 0, 10, 0),
                    AnswerOption("Encontrar la cura para enfermedades complejas", 0, 0, 10),
                    AnswerOption("Mejorar la infraestructura urbana", 8, 2, 0)
                )
            ),
            Question(
                id = 3,
                text = "¿Qué tipo de ambiente de trabajo te resulta más atractivo?",
                options = listOf(
                    AnswerOption("Un laboratorio o centro de cómputo avanzado", 10, 0, 5),
                    AnswerOption("Un estudio creativo o escenario", 0, 10, 0),
                    AnswerOption("Un hospital o clínica ayudando a personas", 0, 0, 10),
                    AnswerOption("Una oficina de planificación de proyectos", 7, 3, 0)
                )
            ),
            Question(
                id = 4,
                text = "¿Cuál de estas materias te gustaba más en la escuela?",
                options = listOf(
                    AnswerOption("Matemáticas y Física", 10, 0, 0),
                    AnswerOption("Literatura y Arte", 0, 10, 0),
                    AnswerOption("Biología y Química", 0, 2, 10),
                    AnswerOption("Historia y Geografía", 2, 6, 2)
                )
            ),
            Question(
                id = 5,
                text = "Al enfrentar un reto difícil, tú:",
                options = listOf(
                    AnswerOption("Analizas los datos y buscas una solución lógica", 10, 0, 0),
                    AnswerOption("Buscas una forma creativa y original de abordarlo", 0, 10, 0),
                    AnswerOption("Piensas en cómo afectará el bienestar de los demás", 0, 0, 10),
                    AnswerOption("Pides opinión a expertos en el tema", 3, 3, 4)
                )
            ),
            Question(
                id = 6,
                text = "¿Qué tipo de proyectos te gustaría liderar?",
                options = listOf(
                    AnswerOption("Crear una aplicación o software innovador", 10, 2, 0),
                    AnswerOption("Diseñar una campaña publicitaria o marca", 0, 10, 0),
                    AnswerOption("Coordinar un programa de salud comunitaria", 0, 0, 10),
                    AnswerOption("Organizar una expedición científica", 5, 0, 5)
                )
            ),
            Question(
                id = 7,
                text = "¿Qué te genera más curiosidad?",
                options = listOf(
                    AnswerOption("Cómo funcionan las máquinas por dentro", 10, 0, 0),
                    AnswerOption("El significado detrás de una obra de arte", 0, 10, 0),
                    AnswerOption("Los procesos que mantienen vivo el cuerpo", 0, 0, 10),
                    AnswerOption("La evolución de las sociedades humanas", 2, 8, 0)
                )
            ),
            Question(
                id = 8,
                text = "En un equipo de trabajo, ¿cuál es tu rol ideal?",
                options = listOf(
                    AnswerOption("El que planifica la estructura y el funcionamiento", 10, 0, 0),
                    AnswerOption("El que aporta las ideas visuales y creativas", 0, 10, 0),
                    AnswerOption("El que se asegura del bienestar de las personas", 0, 0, 10),
                    AnswerOption("El que investiga y recolecta información", 5, 5, 0)
                )
            ),
            Question(
                id = 9,
                text = "¿Cómo prefieres aprender algo nuevo?",
                options = listOf(
                    AnswerOption("Experimentando con herramientas o código", 10, 0, 0),
                    AnswerOption("Observando y analizando estilos o técnicas", 0, 10, 0),
                    AnswerOption("Leyendo sobre casos reales y experiencias humanas", 0, 0, 10),
                    AnswerOption("Siguiendo un manual detallado paso a paso", 7, 0, 3)
                )
            )
        )
    }
}