package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.record.RecordDispatcher
import com.okysoft.annictim.presentation.record.RecordsFragment
import com.okysoft.annictim.presentation.record.RecordsViewModel
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@InstallIn(FragmentComponent::class)
@Module
class RecordsFragmentModule {

    @Provides
    fun provideRecordsFragment(fragment: Fragment) : RecordsFragment {
        return fragment as RecordsFragment
    }

    @Provides
    @Named("RecordsFragment_episodeId")
    fun providesEpisodeId(fragment: RecordsFragment): Int {
        return fragment.episodeId
    }

    @Provides
    fun provideRecordsViewModel(
        factory: RecordsViewModel.Factory,
        target: RecordsFragment
    ) = ViewModelProvider(target, factory).get(RecordsViewModel::class.java)

    @Provides
    @Reusable
    fun providesDispatcher(): RecordDispatcher {
        return RecordDispatcher()
    }
}
