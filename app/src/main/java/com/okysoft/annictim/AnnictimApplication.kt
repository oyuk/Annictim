package com.okysoft.annictim

import com.okysoft.annictim.Di.Component.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class AnnictimApplication: DaggerApplication() {

    private val applicationInjector = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun applicationInjector() = applicationInjector

}