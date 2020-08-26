package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.workDetail.WorkDetailFragment
import com.okysoft.annictim.presentation.workDetail.WorkViewModel
import com.okysoft.domain.model.Work
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class WorkDetailFragmentModule {

    @Provides
    fun provideWorkDetailFragment(fragment: Fragment) : WorkDetailFragment {
        return fragment as WorkDetailFragment
    }

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