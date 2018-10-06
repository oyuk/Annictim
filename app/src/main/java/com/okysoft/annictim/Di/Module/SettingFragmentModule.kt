package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.Presentation.Fragment.SettingFragment
import com.okysoft.annictim.Presentation.ViewModel.SettingViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeSettingFragment(): SettingFragment

    @Module
    class InjectViewModel {

        @Provides
        fun provideSettingViewModel(
                factory: SettingViewModel.Factory,
                target: SettingFragment
        ) = ViewModelProviders.of(target, factory).get(SettingViewModel::class.java)

    }
}