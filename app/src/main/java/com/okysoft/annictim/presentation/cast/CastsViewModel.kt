package com.okysoft.annictim.presentation.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.presentation.CastPaginator
import com.okysoft.domain.model.Cast
import com.okysoft.domain.usecase.CastUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.asSingle
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CastsViewModel constructor(
    useCase: CastUseCase,
    castRequestParams: com.okysoft.data.CastRequestParams
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: CastUseCase,
        private val castRequestParams: com.okysoft.data.CastRequestParams
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CastsViewModel(useCase, castRequestParams) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    @ExperimentalCoroutinesApi
    private val paginator: CastPaginator = CastPaginator(loadMore, refreshPublisher) { page, callback ->
         viewModelScope.launch {
             val response = useCase.get(castRequestParams.copy(page = page))
             callback(response)
        }
    }
    @ExperimentalCoroutinesApi
    val casts: LiveData<List<Cast>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}