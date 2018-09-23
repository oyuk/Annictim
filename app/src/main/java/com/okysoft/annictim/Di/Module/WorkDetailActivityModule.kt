package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.Presentation.ViewModel.WorkViewModel
import com.okysoft.annictim.Presentation.WorkDetailActivity
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
        fun providesWorkId(activity: WorkDetailActivity): Int {
            return activity.workId
        }

        @Provides
        fun provideWorkViewModel(
                factory: WorkViewModel.Factory,
                target: WorkDetailActivity
        ) = ViewModelProviders.of(target, factory).get(WorkViewModel::class.java)

    }

}