package com.okysoft.domain

import com.okysoft.domain.translator.*
import com.okysoft.domain.usecase.*
import com.okysoft.infra.repository.CastRepository
import com.okysoft.infra.repository.ReviewRepository
import com.okysoft.infra.repository.WorkRepository
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
    fun provideStaffUseCase(repository: com.okysoft.infra.repository.StaffRepository): StaffUseCase {
        return StaffUseCaseImpl(repository, StaffTranslator())
    }

    @Provides
    fun provideEpisodeUseCase(repository: com.okysoft.infra.repository.EpisodeRepository): EpisodeUseCase {
        return EpisodeUseCaseImpl(repository, EpisodeTranslator())
    }

    @Provides
    fun provideUserUseCase(repository: com.okysoft.infra.repository.UserRepository): UserUseCase {
        return UserUseCaseImpl(repository, UserTranslator())
    }

    @Provides
    fun provideRecordUseCase(repository: com.okysoft.infra.repository.RecordRepository): RecordUseCase {
        return RecordUseCaseImpl(repository, RecordTranslator())
    }

    @Provides
    fun provideProgramUseCase(repository: com.okysoft.infra.repository.ProgramRepository): ProgramUseCase {
        return ProgramUseCaseImpl(repository, ProgramTranslator())
    }

}