package com.okysoft.annictim

sealed class Result<T> {
    data class Success<T>(var data: T): Result<T>()
    data class Failure<T>(var message: String?, val e: Throwable): Result<T>()

    companion object {
        fun <T> success(data: T): Result<T> = Success(data)
        fun <T> failure(message: String, e: Throwable): Result<T> = Failure(message, e)
    }
}