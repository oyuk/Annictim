package com.okysoft.annictim.presentation.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.presentation.WorkPaginator
import com.okysoft.annictim.usecase.WorkUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.rx2.asSingle
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
    private val context = coroutineContext + job
    @ExperimentalCoroutinesApi
    private val paginator =  WorkPaginator(loadMore, refresh) { page ->
        useCase.request(workRequestParams.copy(page = page)).asSingle(context)
    }
    @ExperimentalCoroutinesApi
    val works: LiveData<List<Work>> = paginator.items.toLiveData()

    fun refresh() {
        refresh.onNext(Unit)
    }

}