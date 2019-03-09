package com.okysoft.annictim.presentation.program

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.infra.api.model.response.ProgramResponse
import com.okysoft.annictim.infra.api.repository.ProgramRepository
import com.okysoft.annictim.presentation.ProgramPaginator
import com.okysoft.annictim.extension.toLiveData
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProgramsViewModel constructor(
    repository: ProgramRepository,
    requestParams: ProgramRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val repository: ProgramRepository,
        private val requestParams: ProgramRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgramsViewModel(repository, requestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    private val paginator: ProgramPaginator = ProgramPaginator(loadMore, refreshPublisher) { page ->
        Single.create<List<ProgramResponse>> {
            GlobalScope.launch(coroutineContext + job) {
                try {
                    val response = repository.get(requestParams.copy(page = page)).await()
                    it.onSuccess(response.programs)
                } catch (throwable: Throwable) {
                    Log.d("", throwable.toString())
                    it.onError(throwable)
                }
            }
        }
    }
    val programs: LiveData<List<ProgramResponse>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}