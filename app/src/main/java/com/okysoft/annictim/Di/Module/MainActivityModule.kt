package com.okysoft.annictim.Di.Module

import android.support.v7.app.AppCompatActivity
import com.okysoft.annictim.Presentation.Activity.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

}
