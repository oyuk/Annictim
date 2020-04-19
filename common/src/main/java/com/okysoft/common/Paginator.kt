package com.okysoft.common

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.withLatestFrom
import java.util.concurrent.TimeUnit


private sealed class Response<T> {
    data class Success<T>(val response: List<T>, val page: Int) : Response<T>()
    data class Error<T>(val throwable: Throwable, val page: Int) : Response<T>()
}

private fun <T> Flowable<Response<T>>.split(): Pair<Flowable<Response.Success<T>>, Flowable<Response.Error<T>>> {
    val successResponse = switchMap {
            when(it) {
                is Response.Success -> Flowable.just(it)
                is Response.Error -> Flowable.empty()
            }
        }
        .share()
    val errorResponse = switchMap {
            when(it) {
                is Response.Success -> Flowable.empty()
                is Response.Error -> Flowable.just(it)
            }
        }
        .share()
    return Pair(successResponse, errorResponse)
}

class Paginator<T>(
    paginationTrigger: Flowable<Unit>,
    refresh: Flowable<Unit>,
    requestCreator: ((Int , (Result<List<T>>) -> Unit) -> Unit)
) {

    val items: Flowable<List<T>>
    val error: Flowable<Throwable>
    val loading: Flowable<Boolean>

    init {
        val _loading = BehaviorProcessor.createDefault(false)
        loading = _loading
        val _items = PublishProcessor.create<List<T>>()
        items = _items
        val currentPage = BehaviorProcessor.createDefault(1)
        val completeRequest = BehaviorProcessor.createDefault(false)

        Flowable.merge(
            refresh.map { 1 },
            items.withLatestFrom(currentPage).map { it.second + 1 }.skip(1)
        )
            .subscribe(currentPage)

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
            .withLatestFrom(currentPage)
            .map { it.second }
            .switchMapSingle { page ->
                _loading.onNext(true)
                return@switchMapSingle Single.create<com.okysoft.common.Response<T>> {
                    requestCreator(page) { response ->
                        response.onSuccess { list ->
                            it.onSuccess(com.okysoft.common.Response.Success(list, page))
                        }.onFailure { error ->
                            it.tryOnError(error)
                        }
                    }
                }.onErrorReturn { com.okysoft.common.Response.Error(it, page) }
            }
            .share()

        val (successResponse, errorResponse) = response.split()

        error = errorResponse.map { it.throwable }

        successResponse
            .scan(listOf()) { t1: List<T>, t2: Response.Success<T> ->
                if (t2.page == 1) { t2.response } else { t1 + t2.response }
            }
            .subscribe(_items)

        successResponse
            .map { it.response.isEmpty() }
            .subscribe(completeRequest)

        Flowable.merge(successResponse.map { false }, error.map {false })
            .subscribe(_loading)
    }

}
