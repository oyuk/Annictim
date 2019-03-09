package com.okysoft.annictim.di.component

import com.okysoft.annictim.application.AnnictimApplication
import com.okysoft.annictim.di.module.*
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
        UseCaseModule::class,
        LoginActivityModule::class,
        BaseActivityModule::class,
        WorksFragmentModule::class,
        EpisodesFragmentModule::class,
        ReviewsFragmentModule::class,
        RecordsFragmentModule::class,
        WorkDetailFragmentModule::class,
        LaunchActivityModule::class,
        SettingFragmentModule::class,
        UserFragmentModule::class,
        SearchFragmentModule::class,
        CastsFragmentModule::class,
        ProgramsFragmentModule::class,
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

