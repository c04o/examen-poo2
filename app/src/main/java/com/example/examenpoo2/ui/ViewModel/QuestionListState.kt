package com.example.examenpoo2.ui.ViewModel

import com.example.examenpoo2.ui.Model.Question

/**
 * QuestionListState: Representa el estado inmutable de la pantalla de lista de preguntas.
 * Se utiliza dentro del ViewModel para notificar a la UI sobre cambios en los datos o el proceso.
 *
 * @property isLoading Indica si actualmente se están buscando datos en el servidor.
 * @property questions Lista de objetos [Question] recuperados exitosamente.
 * @property error Contiene el mensaje de error en caso de que la petición falle.
 */
data class QuestionListState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: String? = null
)
