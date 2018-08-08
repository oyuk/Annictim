package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.Di.ViewModelFactory
import com.okysoft.annictim.Presentation.LoginActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UIModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

}