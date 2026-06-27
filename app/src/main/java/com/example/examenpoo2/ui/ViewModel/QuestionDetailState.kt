package com.example.examenpoo2.ui.ViewModel

import com.example.examenpoo2.ui.Model.Question

/**
 * QuestionDetailState: Representa el estado de la UI cuando se visualiza una única pregunta en detalle.
 * Mantiene la reactividad de la pantalla permitiendo mostrar estados de carga o errores específicos.
 *
 * @property isLoading Indica si se está obteniendo la información de la pregunta desde la fuente de datos.
 * @property question El objeto [Question] cargado. Puede ser nulo si aún no se ha recuperado.
 * @property error Mensaje descriptivo en caso de que no se pueda cargar la información detallada.
 */
data class QuestionDetailState(
    val isLoading: Boolean = false,
    val question: Question? = null,
    val error: String? = null
)
