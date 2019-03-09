package com.okysoft.annictim.di.module

import com.okysoft.annictim.infra.api.repository.CastRepository
import com.okysoft.annictim.infra.api.repository.ReviewRepository
import com.okysoft.annictim.infra.api.repository.StaffRepository
import com.okysoft.annictim.infra.api.repository.WorkRepository
import com.okysoft.annictim.translator.CastTranslator
import com.okysoft.annictim.translator.ReviewTranslator
import com.okysoft.annictim.translator.StaffTranslator
import com.okysoft.annictim.translator.WorkTranslator
import com.okysoft.annictim.usecase.*
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

    @Provides
    fun provideReviewUseCase(repository: ReviewRepository): ReviewUseCase {
        return ReviewUseCaseImpl(repository, ReviewTranslator())
    }

    @Provides
    fun provideStaffUseCase(repository: StaffRepository): StaffUseCase {
        return StaffUseCaseImpl(repository, StaffTranslator())
    }

}