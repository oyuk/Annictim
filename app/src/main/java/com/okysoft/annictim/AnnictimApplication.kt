package com.okysoft.annictim

import com.okysoft.annictim.Di.Component.DaggerApplicationComponent
import com.okysoft.annictim.Di.Module.InfraModule
import dagger.android.support.DaggerApplication

class AnnictimApplication: DaggerApplication() {

    private val applicationInjector = DaggerApplicationComponent.builder()
            .application(this)
            .infraModule(InfraModule(this))
            .build()

    override fun applicationInjector() = applicationInjector

}