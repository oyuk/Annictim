package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModel
import com.okysoft.annictim.Di.ViewModelKey
import com.okysoft.annictim.Presentation.ViewModel.LaunchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LaunchActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    abstract fun bindLaunchViewModel(viewModel: LaunchViewModel): ViewModel


}
