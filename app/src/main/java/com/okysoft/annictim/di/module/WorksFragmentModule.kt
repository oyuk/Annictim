package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.data.WorkRequestParams
import com.okysoft.annictim.presentation.works.WorksFragment
import com.okysoft.annictim.presentation.works.WorksViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class WorksFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeWorksFragment(): WorksFragment

    @Module
    class InjectViewModel {

        @Provides
        fun providesWorksRequestParamModel(fragment: WorksFragment): com.okysoft.data.WorkRequestParams {
            return fragment.workRequestParams
        }

        @Provides
        fun provideWorksViewModel(
            factory: WorksViewModel.Factory,
            target: WorksFragment
        ) = ViewModelProvider(target, factory).get(WorksViewModel::class.java)

    }

}
