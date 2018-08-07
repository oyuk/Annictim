package com.okysoft.annictim.Di.Module

import android.app.Application
import android.content.Context
import dagger.Module

@Module
class InfraModule(application: Application) {

    private val context: Context = application.applicationContext

}