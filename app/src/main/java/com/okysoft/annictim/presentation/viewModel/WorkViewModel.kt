package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.repository.WorkRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WorkViewModel constructor(
        private val repository: WorkRepository,
        private val w: Work
) : ViewModel() {

    class Factory @Inject constructor(
            private val repository: WorkRepository,
            private val work: Work
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(repository, work) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val work: LiveData<Work> = _work
    private val compositeDisposable = CompositeDisposable()

    init {
        _work.postValue(w)
    }


}