package com.example.examenpoo2.ui.service

sealed interface ApiResult<out T> {
    object Loading : ApiResult<Nothing>
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val message: String) : ApiResult<Nothing>
}
