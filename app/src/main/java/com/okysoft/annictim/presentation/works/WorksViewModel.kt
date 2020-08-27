package com.okysoft.annictim.presentation.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.common.Paginator
import com.okysoft.data.WorkRequestParams
import com.okysoft.domain.model.Work
import com.okysoft.domain.usecase.WorkUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WorksViewModel @AssistedInject constructor(
    useCase: WorkUseCase,
    @Assisted workRequestParams: WorkRequestParams
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(workRequestParams: WorkRequestParams): WorksViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            workRequestParams: WorkRequestParams
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(workRequestParams) as T
            }
        }
    }

    val loadMore = PublishProcessor.create<Unit>()
    private val refresh = PublishProcessor.create<Unit>()

    @ExperimentalCoroutinesApi
    private val paginator = Paginator<Work>(loadMore, refresh) { page, callback ->
        viewModelScope.launch {
            try {
                useCase.request(workRequestParams.copy(page = page)).collect {
                    callback(Result.success(it))
                }
            } catch (throwable: Throwable) {
                callback(Result.failure(throwable))
            }
        }
    }

    @ExperimentalCoroutinesApi
    val works: LiveData<List<Work>> = paginator.items.toLiveData()

    fun refresh() {
        refresh.onNext(Unit)
    }

}