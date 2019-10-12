package com.okysoft.annictim.presentation.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.presentation.CastPaginator
import com.okysoft.domain.model.Cast
import com.okysoft.domain.usecase.CastUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.rx2.asSingle
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CastsViewModel constructor(
    useCase: CastUseCase,
    castRequestParams: com.okysoft.data.CastRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: CastUseCase,
        private val castRequestParams: com.okysoft.data.CastRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CastsViewModel(useCase, castRequestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    @ExperimentalCoroutinesApi
    private val paginator: CastPaginator = CastPaginator(loadMore, refreshPublisher) { page ->
        useCase.get(castRequestParams.copy(page = page)).asSingle(coroutineContext)
    }
    @ExperimentalCoroutinesApi
    val casts: LiveData<List<Cast>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}