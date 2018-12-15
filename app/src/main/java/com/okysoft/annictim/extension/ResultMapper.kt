package com.okysoft.annictim.extension

import com.okysoft.annictim.Result
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

fun <T> Observable<T>.toResult(scheduler: Scheduler): Observable<Result<T>> {
    return compose {
        it
                .map {Result.success(it) }
                .onErrorReturn { e -> Result.failure(e.message ?: "unknow", e) }
                .observeOn(scheduler)
    }
}

fun <T> Single<T>.toResult(scheduler: Scheduler): Observable<Result<T>> {
    return toObservable().toResult(scheduler)
}