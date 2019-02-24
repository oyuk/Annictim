package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProviders
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.presentation.fragment.WorkDetailFragment
import com.okysoft.annictim.presentation.viewModel.WorkViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class WorkDetailFragmentModule {

    @ContributesAndroidInjector(modules = [WorkDetailFragmentModule.InjectViewModel::class])
    abstract fun contributeWorkDetailFragment(): WorkDetailFragment

    @Module
    class InjectViewModel {

        @Provides
        fun providesWork(fragment: WorkDetailFragment): Work {
            return fragment.work
        }

        @Provides
        fun provideWorkViewModel(
            factory: WorkViewModel.Factory,
            target: WorkDetailFragment
        ) = ViewModelProviders.of(target, factory).get(WorkViewModel::class.java)

    }

}