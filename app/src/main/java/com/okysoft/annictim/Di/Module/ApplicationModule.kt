package com.okysoft.annictim.Di.Module

import com.okysoft.annictim.ApplicationDispatcher
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.MeStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideMeStore(authRepository: AuthRepository, dispatcher: ApplicationDispatcher): MeStore {
        return MeStore(authRepository, dispatcher)
    }

    @Singleton
    @Provides
    fun provideApplicationDispatcher(): ApplicationDispatcher {
        return ApplicationDispatcher()
    }

}