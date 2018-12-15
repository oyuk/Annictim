package com.okysoft.annictim.di.module

import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.presentation.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

}
