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
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CastsViewModel @AssistedInject constructor(
    useCase: CastUseCase,
    @Assisted castRequestParams: CastRequestParams
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(castRequestParams: CastRequestParams): CastsViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            castRequestParams: CastRequestParams
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(castRequestParams) as T
            }
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    @ExperimentalCoroutinesApi
    private val paginator = Paginator<Cast>(loadMore, refreshPublisher) { page, callback ->
        viewModelScope.launch {
            try {
                useCase.get(castRequestParams.copy(page = page)).collect {
                    callback(Result.success(it))
                }
            } catch (throwable: Throwable) {
                callback(Result.failure(throwable))
            }
        }
    }
    @ExperimentalCoroutinesApi
    val casts: LiveData<List<Cast>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}