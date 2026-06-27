package com.example.examenpoo2.ui.Screen

import com.example.examenpoo2.ui.Model.Question

/**
 * QuestionDetailState: Representa el estado de la interfaz de usuario para la vista de detalle de una pregunta.
 * 
 * @property isLoading Indica si se están cargando los datos de la pregunta.
 * @property question Contiene los datos de la [Question] recuperada, o null si no hay ninguna cargada.
 * @property error Almacena un mensaje informativo en caso de que ocurra un fallo al obtener el detalle.
 */
data class QuestionDetailState(
    val isLoading: Boolean = false,
    val question: Question? = null,
    val error: String? = null
)
