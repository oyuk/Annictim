package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Model.WorksRequestParamModel
import com.okysoft.annictim.API.Repository.WorkRepository
import com.okysoft.annictim.Presentation.WorksRequestType
import com.okysoft.annictim.Result
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


interface PaginateClient<T> {

}

class WorkPaginator<T>(nextPage: Flowable<Unit>, repository: WorkRepository) {

    private val _items = BehaviorProcessor.create<List<T>>()
    val items: Flowable<List<T>> = _items

    init {
        nextPage
                .subscribe({
                    repository.
                })
    }

}

class WorksViewModel constructor(
        private val repository: WorkRepository,
        private val worksRequestParamModel: WorksRequestParamModel
) : ViewModel() {

    class Factory @Inject constructor(
            private val repository: WorkRepository,
            private val worksRequestParamModel: WorksRequestParamModel
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorksViewModel(repository, worksRequestParamModel) as T
        }
    }

    private val _works = MutableLiveData<List<Work>>()
    val works: LiveData<List<Work>> = _works

    private val compositeDisposable = CompositeDisposable()

    init {

    }

    private fun createRequest(): Single<Result<List<Work>>> {
        return when(worksRequestParamModel.worksRequestType) {
            is WorksRequestType.Term -> {
                repository.latest(worksRequestParamModel)
            }
            is WorksRequestType.MeFilterStatus -> {
                repository.me(worksRequestParamModel.worksRequestType.meFilterStatus, 1)
            }
            else -> Single.never()
        }
    }

    fun onCreate() {
        createRequest()
                .subscribe(
                        {
                            when (it) {
                                is Result.Success -> {
                                    _works.postValue((_works.value ?: listOf()).plus(it.data))
                                }
                                is Result.Failure -> {

                                }
                            }
                        },
                        { throwable ->
                            Log.d("", throwable.toString())
                        }
                )
                .addTo(compositeDisposable)
    }

    fun nextPage() {
        createRequest()
                .subscribe( {
                    when (it) {
                        is Result.Success -> {
                            _works.value = _works.value?.plus(it.data)
                        }
                        is Result.Failure -> {

                        }
                    }
                },
                        { throwable -> })
                .addTo(compositeDisposable)
    }

}