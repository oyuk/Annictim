package com.okysoft.annictim.presentation.works

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.infra.api.model.response.Work
import com.okysoft.annictim.infra.api.repository.WorkRepository
import com.okysoft.annictim.presentation.WorkPaginator
import com.okysoft.annictim.extension.toLiveData
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorksViewModel constructor(
    repository: WorkRepository,
    workRequestParams: WorkRequestParams,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val repository: WorkRepository,
        private val workRequestParams: WorkRequestParams,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorksViewModel(repository, workRequestParams, coroutineContext) as T
        }
    }

    private val job = Job()
    val loadMore = PublishProcessor.create<Unit>()
    private val refresh = PublishProcessor.create<Unit>()
    val works: LiveData<List<Work>>
    private val paginator: WorkPaginator

    init {
        val context = coroutineContext + job
        val requestCreator: ((Int) -> Single<List<Work>>) = { page ->
            Single.create<List<Work>> {
                GlobalScope.launch(context) {
                    try {
                        val response = repository.request(workRequestParams, page).await()
                        it.onSuccess(response.works)
                    } catch (throwable: Throwable) {
                        Log.d("", throwable.toString())
                        it.onError(throwable)
                    }
                }
            }
        }

        paginator = WorkPaginator(loadMore, refresh, requestCreator)
        works = paginator.items.toLiveData()
    }

    fun refresh() {
        refresh.onNext(Unit)
    }

}