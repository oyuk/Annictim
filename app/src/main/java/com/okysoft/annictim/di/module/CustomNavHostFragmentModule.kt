package com.okysoft.annictim.di.module

import com.okysoft.annictim.CustomNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CustomNavHostFragmentModule {

    @ContributesAndroidInjector
    abstract fun contoributeCustomNavHostFragment(): CustomNavHostFragment


}
