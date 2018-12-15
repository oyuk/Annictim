package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.model.WorksRequestParamModel
import com.okysoft.annictim.api.repository.WorkRepository
import com.okysoft.annictim.presentation.WorkPaginator
import com.okysoft.annictim.presentation.WorksRequestType
import com.okysoft.annictim.Result
import com.okysoft.annictim.toLiveData
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class WorksViewModel constructor(
        repository: WorkRepository,
        worksRequestParamModel: WorksRequestParamModel
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

    val loadMore = PublishProcessor.create<Unit>()
    val works: LiveData<List<Work>>
    private val paginator: WorkPaginator

    init {
        val requestCreator: ((Int) -> Single<Result<List<Work>>>) = { page ->
            when (worksRequestParamModel.worksRequestType) {
                is WorksRequestType.Term -> {
                    repository.latest(worksRequestParamModel, page)
                }
                is WorksRequestType.MeFilterStatus -> {
                    repository.me(worksRequestParamModel.worksRequestType.meFilterStatus, page)
                }
                else -> Single.never()
            }
        }

        paginator = WorkPaginator(loadMore, requestCreator)
        works = paginator.items.toLiveData()
    }

    fun refresh() {
        paginator.refresh()
    }

}