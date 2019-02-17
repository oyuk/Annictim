package com.okysoft.annictim.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.response.Cast
import com.okysoft.annictim.api.repository.CastRepository
import com.okysoft.annictim.presentation.CastPaginator
import com.okysoft.annictim.presentation.CastRequestParams
import com.okysoft.annictim.toLiveData
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

class CastsViewModel constructor(
    repository: CastRepository,
    castRequestParams: CastRequestParams
) : ViewModel() {

    class Factory @Inject constructor(
        private val repository: CastRepository,
        private val castRequestParams: CastRequestParams
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CastsViewModel(repository, castRequestParams) as T
        }
    }

    val loadMore = PublishProcessor.create<Unit>()
    private val paginator: CastPaginator = CastPaginator(loadMore) { page ->
        repository.get(castRequestParams.copy(page = page))
    }
    val casts: LiveData<List<Cast>> = paginator.items.toLiveData()

    fun refresh() {
        paginator.refresh()
    }

}