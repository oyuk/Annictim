package com.okysoft.annictim.di.module

import com.okysoft.annictim.infra.api.repository.UserRepository
import com.okysoft.annictim.application.ApplicationActionCreator
import com.okysoft.annictim.application.ApplicationDispatcher
import com.okysoft.annictim.infra.api.repository.AuthRepository
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

    @Singleton
    @Provides
    fun provideApplicationActionCreator(userRepository: UserRepository,
                                        dispatcher: ApplicationDispatcher
                                        ): ApplicationActionCreator {
        return ApplicationActionCreator(userRepository, dispatcher)
    }

}