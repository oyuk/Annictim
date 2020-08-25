package com.okysoft.annictim.presentation.program

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.common.Paginator
import com.okysoft.data.ProgramRequestParams
import com.okysoft.domain.model.Program
import com.okysoft.domain.usecase.ProgramUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    private val paginator = Paginator<Program>(loadMore, refreshPublisher) { page, callback ->
        viewModelScope.launch {
            try {
                useCase.get(requestParams.copy(page = page)).collect {
                    callback(Result.success(it))
                }
            } catch (throwable: Throwable) {
                callback(Result.failure(throwable))
            }
        }
    }
    @ExperimentalCoroutinesApi
    val programs: LiveData<List<Program>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}