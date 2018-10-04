package com.okysoft.annictim.Extension

import com.okysoft.annictim.Result
import io.reactivex.Flowable

fun<T> Flowable<Result<T>>.filterSuccess(): Flowable<T> {
    return filter { it is Result.Success }
            .map { (it as Result.Success<T>).data }
}

fun<T> Flowable<Result<T>>.filterError(): Flowable<Throwable> {
    return filter { it is Result.Failure }
            .map { (it as Result.Failure).throwable }
}