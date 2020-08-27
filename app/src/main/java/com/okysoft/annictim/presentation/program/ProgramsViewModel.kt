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
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProgramsViewModel @AssistedInject constructor(
    useCase: ProgramUseCase,
    @Assisted val requestParams: ProgramRequestParams
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(requestParams: ProgramRequestParams): ProgramsViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            requestParams: ProgramRequestParams
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(requestParams) as T
            }
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