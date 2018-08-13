package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModel
import com.okysoft.annictim.Di.ViewModelKey
import com.okysoft.annictim.Presentation.ViewModel.WorksViewModel
import com.okysoft.annictim.Presentation.WorksFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class WorksFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(WorksViewModel::class)
    abstract fun bindWorksViewModel(viewModel: WorksViewModel): ViewModel


    @ContributesAndroidInjector
    abstract fun contributeWorksFragment(): WorksFragment

}
