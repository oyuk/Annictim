package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.workDetail.WorkDetailFragment
import com.okysoft.annictim.presentation.workDetail.WorkViewModel
import com.okysoft.domain.model.Work
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
        ) = ViewModelProvider(target, factory).get(WorkViewModel::class.java)

    }

}