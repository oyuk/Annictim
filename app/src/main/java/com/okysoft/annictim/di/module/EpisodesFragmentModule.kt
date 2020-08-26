package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.episode.EpisodesFragment
import com.okysoft.annictim.presentation.episode.EpisodesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@InstallIn(FragmentComponent::class)
@Module
class EpisodesFragmentModule {

    @Provides
    fun provideEpisodesFragment(fragment: Fragment) : EpisodesFragment {
        return fragment as EpisodesFragment
    }

    @Provides
    @Named("EpisodesFragment_WorkId")
    fun providesWorkId(fragment: EpisodesFragment): Int {
        return fragment.workId
    }

    @Provides
    fun provideEpisodesViewModel(
        factory: EpisodesViewModel.Factory,
        target: EpisodesFragment
    ) = ViewModelProvider(target, factory).get(EpisodesViewModel::class.java)

}
