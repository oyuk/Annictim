package com.okysoft.annictim.presentation

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.extension.filterError
import com.okysoft.annictim.extension.filterSuccess
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.withLatestFrom

abstract class Paginator<T>(
    nextPage: Flowable<Unit>,
    requestCreator: ((Int) -> Single<Result<List<T>>>)
) {

    val items: Flowable<List<T>>
    val error: Flowable<Throwable>
    val loading: Flowable<Boolean>
    private val refresh = PublishProcessor.create<Unit>()
    private val disposable = CompositeDisposable()

    init {
        val _loading = BehaviorProcessor.createDefault(false)
        loading = _loading
        val _items = PublishProcessor .create<List<T>>()
        items = _items
        val currentPage = BehaviorProcessor.createDefault(1)

        Flowable.merge(
            refresh.map { 1 },
            items.withLatestFrom(currentPage).map { it.second + 1 }
        ).subscribe(currentPage)

        val fetchTrigger = Flowable.merge(nextPage, refresh)
            .withLatestFrom(loading)
            .filter { !it.second }
            .share()

        val response = fetchTrigger
            .withLatestFrom(currentPage)
            .switchMapSingle {
                Single.just(Pair(requestCreator(it.second), it.second))
            }
            .share()

        error = response
            .concatMapSingle { it.first }
            .filterError()

        val refreshResponse = response
            .filter { it.second == 1 }
            .concatMapSingle { it.first }
            .filterSuccess()

        val paginationResponse = response
            .filter { it.second > 1 }
            .concatMapSingle { it.first }
            .filterSuccess()
            .withLatestFrom(items)
            .map { it.second + it.first }

        Flowable.merge(refreshResponse, paginationResponse)
            .subscribe (_items)

        Flowable.merge(fetchTrigger.map { true }, items.map { false })
            .subscribe (_loading)
    }

    fun refresh() {
        refresh.onNext(Unit)
    }

}

class WorkPaginator(nextPage: Flowable<Unit>,
                    requestCreator: ((Int) -> Single<Result<List<Work>>>))
    : Paginator<Work>(nextPage, requestCreator)
