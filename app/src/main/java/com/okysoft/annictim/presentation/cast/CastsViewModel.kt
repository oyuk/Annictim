package com.okysoft.annictim.presentation.cast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.infra.api.model.response.Cast
import com.okysoft.annictim.infra.api.repository.CastRepository
import com.okysoft.annictim.presentation.CastPaginator
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import com.okysoft.annictim.extension.toLiveData
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CastsViewModel constructor(
    repository: CastRepository,
    castRequestParams: CastRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val repository: CastRepository,
        private val castRequestParams: CastRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CastsViewModel(repository, castRequestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    private val paginator: CastPaginator = CastPaginator(loadMore, refreshPublisher) { page ->
        Single.create<List<Cast>> {
            GlobalScope.launch(coroutineContext + job) {
                try {
                    val response = repository.get(castRequestParams.copy(page = page)).await()
                    it.onSuccess(response.casts)
                } catch (throwable: Throwable) {
                    Log.d("", throwable.toString())
                    it.onError(throwable)
                }
            }
        }
    }
    val casts: LiveData<List<Cast>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}