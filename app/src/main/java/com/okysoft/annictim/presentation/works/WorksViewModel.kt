package com.okysoft.annictim.presentation.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.common.Paginator
import com.okysoft.domain.model.Work
import com.okysoft.domain.usecase.WorkUseCase
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.*
import javax.inject.Inject

class WorksViewModel constructor(
    useCase: WorkUseCase,
    workRequestParams: com.okysoft.data.WorkRequestParams
) : ViewModel() {

    class Factory @Inject constructor(
        private val useCase: WorkUseCase,
        private val workRequestParams: com.okysoft.data.WorkRequestParams
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorksViewModel(useCase, workRequestParams) as T
        }
    }

    val loadMore = PublishProcessor.create<Unit>()
    private val refresh = PublishProcessor.create<Unit>()

    @ExperimentalCoroutinesApi
    private val paginator = Paginator<Work>(loadMore, refresh) { page, callback ->
        viewModelScope.launch {
            val response = kotlin.runCatching { useCase.request(workRequestParams.copy(page = page)) }
            callback(response)
        }
    }

    @ExperimentalCoroutinesApi
    val works: LiveData<List<Work>> = paginator.items.toLiveData()

    fun refresh() {
        refresh.onNext(Unit)
    }

}