package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProviders
import com.okysoft.data.ProgramRequestParams
import com.okysoft.annictim.presentation.program.ProgramsFragment
import com.okysoft.annictim.presentation.program.ProgramsViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProgramsFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeProgramsFragment(): ProgramsFragment

    @Module
    class InjectViewModel {

        @Provides
        fun providesRequestParams(fragment: ProgramsFragment): com.okysoft.data.ProgramRequestParams {
            return fragment.programRequestParams
        }

        @Provides
        fun provideReviewsViewModel(
            factory: ProgramsViewModel.Factory,
            target: ProgramsFragment
        ) = ViewModelProviders.of(target, factory).get(ProgramsViewModel::class.java)

    }

}
