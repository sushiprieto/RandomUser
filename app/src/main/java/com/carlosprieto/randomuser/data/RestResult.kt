package com.carlosprieto.randomuser.data

sealed class RestResult<out T> {
    data class Success<out T>(val data: T) : RestResult<T>()
    data class Error(val exception: Exception) : RestResult<Nothing>()
}