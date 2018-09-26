package com.okysoft.annictim.Di.Module

import com.okysoft.annictim.Presentation.LaunchActivity
import com.okysoft.annictim.Presentation.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UIModule {

//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [LaunchActivityModule::class])
    internal abstract fun contributeLaunchActivity(): LaunchActivity

}