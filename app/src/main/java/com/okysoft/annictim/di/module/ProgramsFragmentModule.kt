package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.program.ProgramsFragment
import com.okysoft.annictim.presentation.program.ProgramsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class ProgramsFragmentModule {

    @Provides
    fun provideProgramsFragment(fragment: Fragment) : ProgramsFragment {
        return fragment as ProgramsFragment
    }

    @Provides
    fun providesRequestParams(fragment: ProgramsFragment): com.okysoft.data.ProgramRequestParams {
        return fragment.programRequestParams
    }

    @Provides
    fun provideReviewsViewModel(
        factory: ProgramsViewModel.Factory,
        target: ProgramsFragment
    ) = ViewModelProvider(target, factory).get(ProgramsViewModel::class.java)

}
