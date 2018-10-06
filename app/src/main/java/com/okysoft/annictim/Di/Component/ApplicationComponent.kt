package com.okysoft.annictim.Di.Component

import com.okysoft.annictim.AnnictimApplication
import com.okysoft.annictim.Di.Module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        InfraModule::class,
        LoginActivityModule::class,
        BaseActivityModule::class,
        WorksFragmentModule::class,
        EpisodesFragmentModule::class,
        ReviewsFragmentModule::class,
        RecordsFragmentModule::class,
        WorkDetailActivityModule::class,
        LaunchActivityModule::class,
        SettingFragmentModule::class,
        UIModule::class
        ))
interface ApplicationComponent: AndroidInjector<AnnictimApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AnnictimApplication): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(app: AnnictimApplication)

}

