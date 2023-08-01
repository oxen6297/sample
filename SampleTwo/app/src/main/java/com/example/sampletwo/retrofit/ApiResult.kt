package com.example.sampletwo.retrofit


sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val e: Throwable) : ApiResult<Nothing>()
}