package com.okysoft.annictim.Di.Module

import com.okysoft.annictim.Presentation.Activity.*
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

    @ContributesAndroidInjector
    internal abstract fun bindBaseActivity(): BaseActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindRecordsActivity(): RecordsActivity

    @ContributesAndroidInjector
    internal abstract fun bindUserActivity(): UserActivity
}