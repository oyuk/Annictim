package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProviders
import com.okysoft.annictim.presentation.fragment.EpisodesFragment
import com.okysoft.annictim.presentation.viewModel.EpisodesViewModel
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
