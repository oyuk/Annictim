package com.okysoft.common

sealed class Either<T> {
    data class Right<T>(val item: T) : Either<T>()
    data class Left<T>(val throwable: Throwable) : Either<T>()
}
