package com.okysoft.annictim.di.component

import android.app.Application
import com.okysoft.annictim.application.AnnictimApplication
import com.okysoft.annictim.di.module.*
import com.okysoft.domain.UseCaseModule
import com.okysoft.infra.InfraModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = [
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
        ])
interface ApplicationComponent: AndroidInjector<AnnictimApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}

