package com.okysoft.annictim.presentation.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.usecase.WorkUseCase
import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.presentation.WorkPaginator
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorksViewModel constructor(
    useCase: WorkUseCase,
    workRequestParams: WorkRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: WorkUseCase,
        private val workRequestParams: WorkRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorksViewModel(useCase, workRequestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refresh = PublishProcessor.create<Unit>()
    val works: LiveData<List<Work>>
    private val paginator: WorkPaginator

    init {
        val context = coroutineContext + job
        val requestCreator: ((Int) -> Single<List<Work>>) = { page ->
            useCase.get(workRequestParams.copy(page = page))
        }
        paginator = WorkPaginator(loadMore, refresh, requestCreator)
        works = paginator.items.toLiveData()
    }

    fun refresh() {
        refresh.onNext(Unit)
    }

}