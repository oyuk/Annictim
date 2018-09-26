package com.okysoft.annictim.Di.Module

import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.MeStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideMeStore(authRepository: AuthRepository): MeStore {
        return MeStore(authRepository)
    }

}