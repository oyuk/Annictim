package com.okysoft.annictim.presentation

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.model.response.Cast
import com.okysoft.annictim.api.model.response.Work
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.withLatestFrom
import java.util.concurrent.TimeUnit

abstract class Paginator<T>(
    paginationTrigger: Flowable<Unit>,
    requestCreator: ((Int) -> Single<Result<List<T>>>)
) {

    val items: Flowable<List<T>>
    val error: Flowable<Throwable>
    val loading: Flowable<Boolean>
    private val refresh = PublishProcessor.create<Unit>()
    private val disposable = CompositeDisposable()

    data class PaginationResponse<T>(
        val response: Result<List<T>>,
        val page: Int
    ) {

        fun success(): Boolean = response is Result.Success

        fun data(): List<T> = (response as Result.Success).data

        fun error(): Throwable = (response as Result.Failure).throwable
    }

    init {
        val _loading = BehaviorProcessor.createDefault(false)
        loading = _loading
        val _items = PublishProcessor .create<List<T>>()
        items = _items
        val currentPage = BehaviorProcessor.createDefault(1)
        val completeRequest = BehaviorProcessor.createDefault(false)

//        val updateCurrentPage = Flowable.zip(
//            items.map { it.size } ,
//            items.skip(1).map { it.size }, BiFunction { t1: Int, t2: Int -> Pair(t1, t2) })
//            .filter {
//                Log.i("https://api.annict.com/v1/works", "$it")
//                it.first != it.second
//            }
//            .withLatestFrom(currentPage)
//            .map { it.second  + 1 }

        Flowable.merge(
            refresh.map { 1 },
//            updateCurrentPage
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
                requestCreator(page).map { PaginationResponse(it, page) }
            }
            .share()

        error = response
            .filter { !it.success() }
            .map { it.error() }

        val successResponse = response.filter { it.success() }.share()

        successResponse
            .scan(listOf()) { t1: List<T>, t2: PaginationResponse<T> ->
                if (t2.page == 1) { t2.data() } else {  t1 + t2.data() }
            }
            .subscribe(_items)

        successResponse
            .map { it.data().isEmpty() }
            .subscribe(completeRequest)

//        val refreshResponse = response
//            .filter { it.second == 1 }
//            .concatMapSingle { it.first }
//            .doOnNext {
//                Log.i("hoge", it.toString())
//            }
//            .filterSuccess()
//
//        val paginationResponse = response
//            .filter { it.second > 1 }
//            .concatMapSingle { it.first }
//            .filterSuccess()
//            .doOnNext {
//                Log.i("hoge", it.toString())
//            }
//            .withLatestFrom(items)
//            .map { it.second + it.first }

//        Flowable.merge(refreshResponse, paginationResponse)
//            .subscribe (_items)

        //TODO: errorのときにloadignがfalseにならないので修正する
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

class CastPaginator(nextPage: Flowable<Unit>,
                    requestCreator: ((Int) -> Single<Result<List<Cast>>>))
    : Paginator<Cast>(nextPage, requestCreator)

