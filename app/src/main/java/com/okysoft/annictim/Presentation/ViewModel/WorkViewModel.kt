package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Repository.WorkRepository
import com.okysoft.annictim.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class WorkViewModel constructor(
        private val repository: WorkRepository,
        private val workId: Int
) : ViewModel() {

    class Factory @Inject constructor(
            private val repository: WorkRepository,
            private val workId: Int
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(repository, workId) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val work: LiveData<Work> = _work
    private val compositeDisposable = CompositeDisposable()

    init {
        fetch()
    }

    private fun fetch() {
        repository.get(id = workId)
                .subscribe(
                        {
                            when (it) {
                                is Result.Success -> {
                                    _work.postValue(it.data)
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


}