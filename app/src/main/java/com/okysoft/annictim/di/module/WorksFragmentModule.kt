package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.works.MeWorksTabPagerFragment
import com.okysoft.data.WorkRequestParams
import com.okysoft.annictim.presentation.works.WorksFragment
import com.okysoft.annictim.presentation.works.WorksTabPagerFragment
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
        fun providesPosition(fragment: WorksFragment): Int? {
            return fragment.position
        }

        @Provides
        fun provideWorksViewModel(
            factory: WorksViewModel.Factory,
            worksFragment: WorksFragment,
            position: Int?
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

}
