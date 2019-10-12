package com.okysoft.annictim.presentation.program

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.presentation.ProgramPaginator
import com.okysoft.data.ProgramRequestParams
import com.okysoft.domain.model.Program
import com.okysoft.domain.usecase.ProgramUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.rx2.asSingle
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProgramsViewModel constructor(
    useCase: ProgramUseCase,
    requestParams: ProgramRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: ProgramUseCase,
        private val requestParams: ProgramRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgramsViewModel(useCase, requestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    @ExperimentalCoroutinesApi
    private val paginator: ProgramPaginator = ProgramPaginator(loadMore, refreshPublisher) { page ->
        useCase.get(requestParams.copy(page = page)).asSingle(coroutineContext)
    }
    @ExperimentalCoroutinesApi
    val programs: LiveData<List<Program>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}