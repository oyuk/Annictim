package com.okysoft.annictim.Di.Component

import com.okysoft.annictim.AnnictimApplication
import com.okysoft.annictim.Di.Module.InfraModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = arrayOf(AndroidSupportInjectionModule::class, InfraModule::class))
interface ApplicationComponent: AndroidInjector<AnnictimApplication> {

    override fun inject(application: AnnictimApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AnnictimApplication): Builder

        @BindsInstance
        fun infraModule(infraModule: InfraModule): Builder

        fun build(): ApplicationComponent
    }

}

