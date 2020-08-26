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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
            try {
                useCase.request(workRequestParams.copy(page = page)).collect {
                    callback(Result.success(it))
                }
            } catch (throwable: Throwable) {
                callback(Result.failure(throwable))
            }
        }
    }

    @ExperimentalCoroutinesApi
    val works: LiveData<List<Work>> = paginator.items.toLiveData()

    fun refresh() {
        refresh.onNext(Unit)
    }

}