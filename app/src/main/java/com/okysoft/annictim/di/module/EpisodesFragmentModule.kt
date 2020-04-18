package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.episode.EpisodesFragment
import com.okysoft.annictim.presentation.episode.EpisodesViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class EpisodesFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeEpisodesFragment(): EpisodesFragment

    @Module
    class InjectViewModel {

        @Provides
        fun providesWorkId(fragment: EpisodesFragment): Int {
            return fragment.workId
        }

        @Provides
        fun provideEpisodesViewModel(
            factory: EpisodesViewModel.Factory,
            target: EpisodesFragment
        ) = ViewModelProvider(target, factory).get(EpisodesViewModel::class.java)

    }

}
