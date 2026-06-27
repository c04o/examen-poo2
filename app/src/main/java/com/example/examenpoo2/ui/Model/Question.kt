package com.example.examenpoo2.ui.Model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Representa una pregunta individual.
 * Se han añadido anotaciones de Gson (@SerializedName) para que funcione con la API de prueba.
 */
@Serializable
data class Question(
    val id: Int,
    
    @SerializedName("title") // Mapea el campo 'title' de jsonplaceholder a nuestro 'text'
    val text: String,
    
    // Usamos un valor por defecto. 
    // Nota: Gson puede poner esto como null si no viene en el JSON, 
    // por lo que lo trataremos con seguridad en la UI.
    val options: List<AnswerOption>? = emptyList()
)

@Serializable
data class AnswerOption(
    val id: Int = 0,
    val text: String = "Opción de prueba",
    val category: String = "General"
)
