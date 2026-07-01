package com.example.examenpoo2.ui.Service

/**
 * ApiResult: Interfaz sellada (sealed) que representa el resultado de una operación de red o proceso asíncrono.
 * Proporciona una forma segura y estructurada de manejar los diferentes estados de la petición en la UI.
 * 
 * @param T El tipo de datos que se espera recibir en caso de éxito.
 */
sealed interface ApiResult<out T> {
    
    /**
     * Representa un resultado exitoso que contiene los datos recuperados.
     * @property data Los datos de tipo [T] obtenidos de la operación.
     */
    data class Success<out T>(val data: T) : ApiResult<T>
    
    /**
     * Representa un fallo en la operación.
     * @property message Descripción legible del error para mostrar al usuario.
     * @property throwable La excepción original que causó el fallo (opcional).
     */
    data class Error(val message: String, val throwable: Throwable? = null) : ApiResult<Nothing>
    
    /**
     * Estado inicial o de espera que indica que la operación está actualmente en curso.
     */
    data object Loading : ApiResult<Nothing>
}
