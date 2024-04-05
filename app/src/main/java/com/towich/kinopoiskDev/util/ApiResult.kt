package com.towich.kinopoiskDev.util

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val error: String) : ApiResult<Nothing>()
}
