package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModel
import com.okysoft.annictim.di.ViewModelKey
import com.okysoft.annictim.presentation.viewModel.LaunchViewModel
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
