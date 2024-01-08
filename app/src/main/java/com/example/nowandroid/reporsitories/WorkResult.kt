package com.example.nowandroid.reporsitories

sealed class WorkResult<out R> {
    data class Success<out T>(val data: T) : WorkResult<T>()
    data class Error(val exception: Exception) : WorkResult<Nothing>()
    data object Loading : WorkResult<Nothing>()
}