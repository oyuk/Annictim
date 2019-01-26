package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.WorksRequestParamModel
import com.okysoft.annictim.api.repository.WorkRepository
import com.okysoft.annictim.presentation.WorksRequestType
import com.okysoft.annictim.toLiveData
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.withLatestFrom
import javax.inject.Inject

class SearchViewModel constructor(
    repository: WorkRepository
) : ViewModel() {

    class Factory @Inject constructor(
        private val repository: WorkRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(repository) as T
        }
    }

    private val titleProcessor = BehaviorProcessor.createDefault("")
    private val tappedSearch = PublishProcessor.create<Unit>()

    val transitionTo: LiveData<WorksRequestParamModel> =
        tappedSearch.withLatestFrom(titleProcessor)
            .map { it.second }
            .map {
                WorksRequestParamModel(
                    WorksRequestType.Search(title = it),
                    WorksRequestParamModel.Fields.All)
            }
            .toLiveData()

    fun setTitle(title: CharSequence) {
        titleProcessor.onNext(title.toString())
    }

    fun tappedSearch() {
        tappedSearch.onNext(Unit)
    }

}