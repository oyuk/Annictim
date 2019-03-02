package com.okysoft.annictim.di.module

import com.okysoft.annictim.presentation.widget.CustomNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CustomNavHostFragmentModule {

    @ContributesAndroidInjector
    abstract fun contoributeCustomNavHostFragment(): CustomNavHostFragment


}
