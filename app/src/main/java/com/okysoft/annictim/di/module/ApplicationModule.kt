package com.okysoft.annictim.di.module

import com.okysoft.annictim.MeStore
import com.okysoft.infra.ApplicationActionCreator
import com.okysoft.infra.ApplicationDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideMeStore(authRepository: com.okysoft.infra.repository.AuthRepository, dispatcher: ApplicationDispatcher): MeStore {
        return MeStore(authRepository, dispatcher)
    }

    @Singleton
    @Provides
    fun provideApplicationDispatcher(): ApplicationDispatcher {
        return ApplicationDispatcher()
    }

    @Singleton
    @Provides
    fun provideApplicationActionCreator(userRepository: com.okysoft.infra.repository.UserRepository,
                                        dispatcher: ApplicationDispatcher
                                        ): ApplicationActionCreator {
        return ApplicationActionCreator(userRepository, dispatcher)
    }

}