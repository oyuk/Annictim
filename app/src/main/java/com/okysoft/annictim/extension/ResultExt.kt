package com.okysoft.annictim.extension

import com.okysoft.annictim.Result
import io.reactivex.Flowable
import io.reactivex.Single

fun<T> Flowable<Result<T>>.filterSuccess(): Flowable<T> {
    return filter { it is Result.Success }
            .map { (it as Result.Success<T>).data }
}

fun<T> Single<Result<T>>.filterSuccess(): Single<T> {
    return filter { it is Result.Success }
        .map { (it as Result.Success<T>).data }
        .toSingle()
}

fun<T> Flowable<Result<T>>.filterError(): Flowable<Throwable> {
    return filter { it is Result.Failure }
            .map { (it as Result.Failure).throwable }
}

inline fun <reified R> Flowable<*>.filterMap(): Flowable<R> {
    return filter { it is R }.map { (it as R) }
}