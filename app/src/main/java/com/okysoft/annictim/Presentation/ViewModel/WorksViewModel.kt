package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Repository.WorkRepository
import com.okysoft.annictim.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class WorksViewModel  @Inject constructor(private val repository: WorkRepository) : ViewModel() {

    private val _works = MutableLiveData<List<Work>>()
    val works: LiveData<List<Work>> = _works

    private val paginator = PublishProcessor.create<Int>()

    private val compositeDisposable = CompositeDisposable()

    private val page = PublishRelay.create<Int>()

    init {

    }

    fun onCreate() {
        repository.latest()
                .subscribeBy {
                    when (it) {
                        is Result.Success -> {
                            _works.postValue((_works.value ?: listOf()).plus(it.data))
                        }
                        is Result.Failure -> {

                        }
                    }
                }
                .addTo(compositeDisposable)
    }

    fun nextPage() {
        repository.latest()
                .subscribeBy {
                    Log.i("hoge", it.toString())
                    when (it) {
                        is Result.Success -> {
                            _works.value = _works.value?.plus(it.data)
                        }
                        is Result.Failure -> {

                        }
                    }
                }
                .addTo(compositeDisposable)
    }
}