package com.okysoft.annictim.presentation.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.common.Either
import com.okysoft.common.PaginatablePaginator
import com.okysoft.data.WorkRequestParams
import com.okysoft.domain.usecase.WorkUseCase
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.repository.WorkFeedRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WorksViewModel @AssistedInject constructor(
    useCase: WorkUseCase,
    private val repository: WorkFeedRepository,
    @Assisted workRequestParams: WorkRequestParams
) : ViewModel() {

    @AssistedFactory
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
    private val paginator = PaginatablePaginator<WorkFeed>(loadMore, refresh) { cursor, callback ->
        viewModelScope.launch {
            try {
            repository.fetch(workRequestParams.season ?: "", cursor)
                .collect {
                    callback(Either.Right(it))
                }
            } catch (throwable: Throwable) {
                callback(Either.Left(throwable))
            }
        }
    }

    @ExperimentalCoroutinesApi
    val works: LiveData<List<WorkFeed>> = paginator.items.toLiveData()

    fun refresh() {
        refresh.onNext(Unit)
    }


}
