package com.okysoft.annictim.presentation.works

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.okysoft.data.WorkRequestParams
import com.okysoft.infra.WorkFeedPagingSource
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.repository.WorkFeedRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.flow.Flow

class WorksViewModel @AssistedInject constructor(
    repository: WorkFeedRepository,
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

//    val loadMore = PublishProcessor.create<Unit>()
    private val refresh = PublishProcessor.create<Unit>()

//    @ExperimentalCoroutinesApi
//    private val paginator = PaginatablePaginator<WorkFeed>(loadMore, refresh) { cursor, callback ->
//        viewModelScope.launch {
//            try {
//            repository.fetch(workRequestParams.season ?: "", cursor)
//                .collect {
//                    callback(Either.Right(it))
//                }
//            } catch (throwable: Throwable) {
//                callback(Either.Left(throwable))
//            }
//        }
//    }

    val works: Flow<PagingData<WorkFeed>> = Pager(PagingConfig(pageSize = 20)) {
        WorkFeedPagingSource(repository, workRequestParams)
    }
        .flow.cachedIn(viewModelScope)

    fun refresh() {
//        refresh.onNext(Unit)
    }


}
