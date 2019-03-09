package com.okysoft.annictim.di.module

import com.okysoft.annictim.infra.api.repository.CastRepository
import com.okysoft.annictim.infra.api.repository.WorkRepository
import com.okysoft.annictim.translator.CastTranslator
import com.okysoft.annictim.translator.WorkTranslator
import com.okysoft.annictim.usecase.CastUseCase
import com.okysoft.annictim.usecase.CastUseCaseImpl
import com.okysoft.annictim.usecase.WorkUseCase
import com.okysoft.annictim.usecase.WorkUseCaseImpl
import dagger.Module
import dagger.Provides


@Module
class UseCaseModule {

    @Provides
    fun provideWorkUseCase(repository: WorkRepository): WorkUseCase {
        return WorkUseCaseImpl(repository, WorkTranslator())
    }

    @Provides
    fun provideCastUseCase(repository: CastRepository): CastUseCase {
        return CastUseCaseImpl(repository, CastTranslator())
    }

}