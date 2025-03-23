package com.iti.androidusingkotlin.productsusingmvvm.data


sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val error: Exception): Result<Nothing>()
    data object Loading: Result<Nothing>()
}

//fun<T> safeCall(): Result<T> {
//
//}