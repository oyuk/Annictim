package com.okysoft.annictim.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.response.Program
import com.okysoft.annictim.api.repository.ProgramRepository
import com.okysoft.annictim.presentation.ProgramPaginator
import com.okysoft.annictim.presentation.ProgramRequestParams
import com.okysoft.annictim.toLiveData
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
        Single.create<List<Program>> {
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
    val programs: LiveData<List<Program>> = paginator.items.toLiveData()

    fun refresh() {
        refreshPublisher.onNext(Unit)
    }

}