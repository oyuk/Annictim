package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModel
import com.okysoft.annictim.Di.ViewModelKey
import com.okysoft.annictim.Presentation.ViewModel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel


}
