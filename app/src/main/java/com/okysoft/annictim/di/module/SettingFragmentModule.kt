package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.setting.SettingFragment
import com.okysoft.annictim.presentation.setting.SettingViewModel
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
        ) = ViewModelProvider(target, factory).get(SettingViewModel::class.java)

    }
}