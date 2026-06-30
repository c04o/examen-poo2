package com.example.examenpoo2.ui.ViewModel

import com.example.examenpoo2.ui.Model.Question

/**
 * Representa el estado de la interfaz de usuario para la pantalla de detalle de una pregunta.
 * 
 * Esta clase encapsula toda la información necesaria que la UI debe mostrar en un momento dado,
 * permitiendo una gestión de estado centralizada y reactiva siguiendo el patrón Unidirectional Data Flow (UDF).
 *
 * @property isLoading Indica si se está realizando una operación de carga de datos.
 * @property question El objeto [Question] que contiene los detalles de la pregunta cargada, o null si no está disponible.
 * @property error Mensaje de error descriptivo en caso de que ocurra un fallo durante la carga o procesamiento.
 */
data class QuestionDetailState(
    val isLoading: Boolean = false,
    val question: Question? = null,
    val error: String? = null
)
