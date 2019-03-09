package com.okysoft.annictim.presentation.program

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.Program
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.annictim.infra.api.model.request.ProgramRequestParams
import com.okysoft.annictim.presentation.ProgramPaginator
import com.okysoft.annictim.usecase.ProgramUseCase
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProgramsViewModel constructor(
    usecase: ProgramUseCase,
    requestParams: ProgramRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: ProgramUseCase,
        private val requestParams: ProgramRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgramsViewModel(useCase, requestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refreshPublisher = PublishProcessor.create<Unit>()
    private val paginator: ProgramPaginator = ProgramPaginator(loadMore, refreshPublisher) { page ->
        Single.create<List<Program>> {
            GlobalScope.launch(coroutineContext + job) {
                try {
                    val response = usecase.get(requestParams.copy(page = page)).await()
                    it.onSuccess(response)
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