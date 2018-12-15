package com.okysoft.annictim.di.module

import android.arch.lifecycle.ViewModel
import com.okysoft.annictim.di.ViewModelKey
import com.okysoft.annictim.presentation.viewModel.LoginViewModel
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
