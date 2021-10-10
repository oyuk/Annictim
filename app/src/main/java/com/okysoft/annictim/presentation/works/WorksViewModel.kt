package com.okysoft.annictim.presentation.works

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.okysoft.annictim.extension.extractActivity
import com.okysoft.data.WorkRequestParams
import com.okysoft.infra.WorkFeedPagingSource
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.repository.WorkFeedRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.flow.Flow

class WorksViewModel @AssistedInject constructor(
    repository: WorkFeedRepository,
    @Assisted workRequestParams: WorkRequestParams
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(workRequestParams: WorkRequestParams): WorksViewModel
    }

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ActivityCreatorEntryPoint {
        fun getWorksViewModelFactory(): Factory
    }

    companion object {

        fun provideFactory(
            context: Context,
            workRequestParams: WorkRequestParams
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val activity = context.extractActivity()
                val factory = EntryPoints.get(activity, ActivityCreatorEntryPoint::class.java)
                    .getWorksViewModelFactory()
                return factory.create(workRequestParams) as T
            }
        }
    }

    val works: Flow<PagingData<WorkFeed>> = Pager(PagingConfig(pageSize = 20)) {
        WorkFeedPagingSource(repository, workRequestParams)
    }
        .flow.cachedIn(viewModelScope)
}
