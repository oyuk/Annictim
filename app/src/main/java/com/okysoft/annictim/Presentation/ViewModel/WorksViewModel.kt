package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Model.WorksRequestParamModel
import com.okysoft.annictim.API.Repository.WorkRepository
import com.okysoft.annictim.Extension.filterError
import com.okysoft.annictim.Extension.filterSuccess
import com.okysoft.annictim.Presentation.WorkPaginator
import com.okysoft.annictim.Presentation.WorksRequestType
import com.okysoft.annictim.Result
import com.okysoft.annictim.toLiveData
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
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

    private val paginator = WorkPaginator(
            loadMore,
            repository,
            worksRequestParamModel
    )

    val works: LiveData<List<Work>> = paginator.items.toLiveData()

    fun refresh() {
        paginator.refresh()
    }

}