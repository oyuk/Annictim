package com.okysoft.annictim.presentation.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.common.Paginator
import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Cast
import com.okysoft.domain.usecase.CastUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CastsViewModel constructor(
    useCase: CastUseCase,
    castRequestParams: CastRequestParams
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: CastUseCase,
        private val castRequestParams: CastRequestParams
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
    private val paginator = Paginator<Cast>(loadMore, refreshPublisher) { page, callback ->
        viewModelScope.launch {
            val response = kotlin.runCatching { useCase.get(castRequestParams.copy(page = page)) }
            callback(response)
        }
    }
    @ExperimentalCoroutinesApi
    val casts: LiveData<List<Cast>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}