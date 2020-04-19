package com.okysoft.annictim.presentation.program

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.presentation.ProgramPaginator
import com.okysoft.data.ProgramRequestParams
import com.okysoft.domain.model.Program
import com.okysoft.domain.usecase.ProgramUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asSingle
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProgramsViewModel constructor(
    useCase: ProgramUseCase,
    requestParams: ProgramRequestParams
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: ProgramUseCase,
        private val requestParams: ProgramRequestParams
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgramsViewModel(useCase, requestParams) as T
        }
    }

    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()

    @ExperimentalCoroutinesApi
    private val paginator: ProgramPaginator = ProgramPaginator(loadMore, refreshPublisher) { page, callback ->
        viewModelScope.launch {
            callback(useCase.get(requestParams.copy(page = page)))
        }
    }
    @ExperimentalCoroutinesApi
    val programs: LiveData<List<Program>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}