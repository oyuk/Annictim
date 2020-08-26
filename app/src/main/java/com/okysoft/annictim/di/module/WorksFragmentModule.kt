package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.works.MeWorksTabPagerFragment
import com.okysoft.annictim.presentation.works.WorksFragment
import com.okysoft.annictim.presentation.works.WorksTabPagerFragment
import com.okysoft.annictim.presentation.works.WorksViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
class WorksFragmentModule {

    @Provides
    fun provideWorksFragment(fragment: Fragment) : WorksFragment {
        return fragment as WorksFragment
    }

    @Provides
    fun providesWorksRequestParamModel(fragment: WorksFragment): com.okysoft.data.WorkRequestParams {
        return fragment.workRequestParams
    }

    @Provides
    @Named("WorksFragment_Position")
    fun providesPosition(fragment: WorksFragment): Int? {
        return fragment.position
    }

    @Provides
    fun provideWorksViewModel(
        factory: WorksViewModel.Factory,
        worksFragment: WorksFragment,
        @Named("WorksFragment_Position") position: Int?
    ): WorksViewModel {
        val parent = worksFragment.parentFragment
        val (target, key) = when (parent) {
            is WorksTabPagerFragment -> { Pair(parent, "${parent.javaClass.name}${position}") }
            is MeWorksTabPagerFragment -> { Pair(parent, "${parent.javaClass.name}${position}") }
            else -> { Pair(worksFragment, null) }
        }
        return key?.let {
            ViewModelProvider(target, factory).get(it, WorksViewModel::class.java)
        } ?: ViewModelProvider(target, factory).get(WorksViewModel::class.java)
    }


}
