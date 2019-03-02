package com.okysoft.annictim.application

import com.okysoft.annictim.di.component.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class AnnictimApplication: DaggerApplication() {

    private val applicationInjector = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun applicationInjector() = applicationInjector

}