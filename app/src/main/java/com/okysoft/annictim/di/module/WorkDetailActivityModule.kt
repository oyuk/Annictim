package com.okysoft.annictim.di.module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.presentation.viewModel.WorkViewModel
import com.okysoft.annictim.presentation.activity.WorkDetailActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class WorkDetailActivityModule {

    @ContributesAndroidInjector(modules = [WorkDetailActivityModule.InjectViewModel::class])
    abstract fun contributeWorkDetailActivity(): WorkDetailActivity

    @Module
    class InjectViewModel {

        @Provides
        fun providesWork(activity: WorkDetailActivity): Work {
            return activity.work
        }

        @Provides
        fun provideWorkViewModel(
                factory: WorkViewModel.Factory,
                target: WorkDetailActivity
        ) = ViewModelProviders.of(target, factory).get(WorkViewModel::class.java)

    }

}