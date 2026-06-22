package com.example.examenpoo2.ui.Service

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>
    data class Error(val message: String, val throwable: Throwable? = null) : ApiResult<Nothing>
    data object Loading : ApiResult<Nothing>
}
