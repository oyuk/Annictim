package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.Presentation.Fragment.EpisodesFragment
import com.okysoft.annictim.Presentation.ViewModel.EpisodesViewModel
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
        ) = ViewModelProviders.of(target, factory).get(EpisodesViewModel::class.java)

    }

}
