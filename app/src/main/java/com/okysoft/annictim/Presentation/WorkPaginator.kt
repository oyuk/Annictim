package com.okysoft.annictim.Presentation

import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Model.WorksRequestParamModel
import com.okysoft.annictim.API.Repository.WorkRepository
import com.okysoft.annictim.Extension.filterError
import com.okysoft.annictim.Extension.filterSuccess
import com.okysoft.annictim.Result
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom

abstract class Paginator<T>(nextPage: Flowable<Unit>) {

    val items: Flowable<List<Work>>
    val error: Flowable<Throwable>
    val loading: Flowable<Boolean>
    private val refresh = PublishProcessor.create<Unit>()
    private val disposable = CompositeDisposable()

    init {
        val _loading = BehaviorProcessor.createDefault(false)
        loading = _loading
        val _items = PublishProcessor .create<List<Work>>()
        items = _items
        val currentPage = BehaviorProcessor.createDefault(1)

        Flowable.merge(
                refresh.map { 1 },
                items.withLatestFrom(currentPage).map { it.second + 1 }
        )
                .subscribe {
                    currentPage.onNext(it)
                }.addTo(disposable)

        val fetchTrigger = Flowable.merge(nextPage, refresh)
                .withLatestFrom(loading)
                .filter { !it.second }
                .share()

        val response = fetchTrigger
                .withLatestFrom(currentPage)
                .concatMapSingle {
                    Single.just(Pair(
                            createRequest(),
                            it.second))
                }
                .share()

        val errorResponse = response
                .concatMapSingle { it.first }
                .filterError()
                .share()

        val refreshResponse = response
                .filter { it.second == 1 }
                .concatMapSingle { it.first }
                .filterSuccess()
                .share()

        val paginationResponse = response
                .filter { it.second > 1 }
                .concatMapSingle { it.first }
                .filterSuccess()
                .withLatestFrom(items)
                .map { it.second + it.first }
                .share()

        error = errorResponse

        Flowable.merge(refreshResponse, paginationResponse)
                .subscribe {
                    _items.onNext(it)
                }.addTo(disposable)

        Flowable.merge(fetchTrigger.map { true },
                errorResponse.map { false },
                refreshResponse.map { false },
                paginationResponse.map { false })
                .subscribe {
                    _loading.onNext(it)
                }.addTo(disposable)
    }

    fun refresh() {
        refresh.onNext(Unit)
    }

    abstract internal fun createRequest(): Single<Result<List<Work>>>

}

class WorkPaginator(nextPage: Flowable<Unit>,
                    repository: WorkRepository,
                    worksRequestTypeModel: WorksRequestParamModel
) {

    val items: Flowable<List<Work>>
    val error: Flowable<Throwable>
    val loading: Flowable<Boolean>
    private val refresh = PublishProcessor.create<Unit>()
    private val disposable = CompositeDisposable()

    init {
        val _loading = BehaviorProcessor.createDefault(false)
        loading = _loading
        val _items = PublishProcessor .create<List<Work>>()
        items = _items
        val currentPage = BehaviorProcessor.createDefault(1)

        Flowable.merge(
                refresh.map { 1 },
                items.withLatestFrom(currentPage).map { it.second + 1 }
        )
                .subscribe {
                    currentPage.onNext(it)
                }.addTo(disposable)

        val fetchTrigger = Flowable.merge(nextPage, refresh)
                .withLatestFrom(loading)
                .filter { !it.second }
                .share()

        val response = fetchTrigger
                .withLatestFrom(currentPage)
                .concatMapSingle {
                    Single.just(Pair(
                            createRequest(worksRequestTypeModel, repository, it.second),
                            it.second))
                }
                .share()

        val errorResponse = response
                .concatMapSingle { it.first }
                .filterError()
                .share()

        val refreshResponse = response
                .filter { it.second == 1 }
                .concatMapSingle { it.first }
                .filterSuccess()
                .share()

        val paginationResponse = response
                .filter { it.second > 1 }
                .concatMapSingle { it.first }
                .filterSuccess()
                .withLatestFrom(items)
                .map { it.second + it.first }
                .share()

        error = errorResponse

        Flowable.merge(refreshResponse, paginationResponse)
                .subscribe {
                    _items.onNext(it)
                }.addTo(disposable)

        Flowable.merge(fetchTrigger.map { true },
                errorResponse.map { false },
                refreshResponse.map { false },
                paginationResponse.map { false })
                .subscribe {
                    _loading.onNext(it)
                }.addTo(disposable)
    }

    fun refresh() {
        refresh.onNext(Unit)
    }

    private fun createRequest(worksRequestParamModel: WorksRequestParamModel,
                              repository: WorkRepository,
                              page: Int
    ): Single<Result<List<Work>>> {
        return when(worksRequestParamModel.worksRequestType) {
            is WorksRequestType.Term -> {
                repository.latest(worksRequestParamModel, page)
            }
            is WorksRequestType.MeFilterStatus -> {
                repository.me(worksRequestParamModel.worksRequestType.meFilterStatus, page)
            }
            else -> Single.never()
        }
    }
}
