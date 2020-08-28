package com.okysoft.common

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.withLatestFrom
import java.util.concurrent.TimeUnit

private fun <T> Flowable<Either<T>>.split(): Pair<Flowable<Either.Right<T>>, Flowable<Either.Left<T>>> {
    val successResponse = switchMap {
        when (it) {
            is Either.Right -> Flowable.just(it)
            is Either.Left ->  Flowable.empty()
        }
    }.share()

    val errorResponse = switchMap {
        when (it) {
            is Either.Right -> Flowable.empty()
            is Either.Left -> Flowable.just(it)
        }
    }.share()

    return Pair(successResponse, errorResponse)
}

class PaginatablePaginator<T>(
    paginationTrigger: Flowable<Unit>,
    refresh: Flowable<Unit>,
    requestCreator: ((String , (Either<PaginatableResponse<T>>) -> Unit) -> Unit)
) {

    val items: Flowable<List<T>>
    val error: Flowable<Throwable>
    val loading: Flowable<Boolean>

    init {
        val _loading = BehaviorProcessor.createDefault(false)
        loading = _loading
        val _items = BehaviorProcessor.createDefault(emptyList<T>())
        items = _items
        val nextCursor = BehaviorProcessor.createDefault<String>("")
        val completeRequest = BehaviorProcessor.createDefault(false)

        refresh.map { "" }
            .subscribe(nextCursor)

        val fetchTrigger = Flowable.merge(
            paginationTrigger
                .debounce(500, TimeUnit.MILLISECONDS)
                .withLatestFrom(completeRequest)
                .filter { !it.second },
            refresh)
            .withLatestFrom(loading)
            .filter { !it.second }
            .share()

        val response = fetchTrigger
            .withLatestFrom(nextCursor)
            .map { it.second }
            .switchMapSingle { cursor ->
                _loading.onNext(true)
                return@switchMapSingle Single.create<Either<PaginatableResponse<T>>> {
                    requestCreator(cursor) { response ->
                        when (response) {
                            is Either.Right -> it.onSuccess(Either.Right(response.item))
                            is Either.Left -> it.tryOnError(response.throwable)
                        }
                    }
                }.onErrorReturn {
                    Either.Left(it)
                }
            }
            .share()

        val (successResponse, errorResponse) = response.split()

        error = errorResponse.map { it.throwable }

        successResponse
            .map { it.item.items }
            .withLatestFrom(_items)
            .map {
                return@map if (nextCursor.value.isNullOrBlank()) {
                    it.first
                } else {
                    it.second + it.first
                }
            }
            .subscribe(_items)

        successResponse
            .map { !it.item.hasNext }
            .subscribe(completeRequest)

        successResponse
            .map { it.item.nextCursor }
            .subscribe(nextCursor)

        Flowable.merge(successResponse.map { false }, error.map {false })
            .subscribe(_loading)
    }

}
