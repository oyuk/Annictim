package com.okysoft.domain

import com.okysoft.domain.translator.*
import com.okysoft.domain.usecase.*
import com.okysoft.infra.repository.CastRepository
import com.okysoft.infra.repository.ReviewRepository
import com.okysoft.infra.repository.WorkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent


class UseCaseModule {

    @Module
    @InstallIn(ViewModelComponent::class)
    class ViewModelUseCaseModule {

        @Provides
        @ViewModelScoped
        fun provideUserUseCase(repository: com.okysoft.infra.repository.UserRepository): UserUseCase {
            return UserUseCaseImpl(repository, UserTranslator())
        }

    }


    @Module
    @InstallIn(ActivityComponent::class)
    class ActivityUseCaseModule {

        @Provides
        @ActivityScoped
        fun provideWorkDetailUseCase(repository: WorkRepository): WorkDetailUseCase {
            return WorkDetailUseCaseImpl(repository, WorkDetailTranslator())
        }

        @Provides
        @ActivityScoped
        fun provideCastUseCase(repository: CastRepository): CastUseCase {
            return CastUseCaseImpl(repository, CastTranslator(
                characterTranslator = CharacterTranslator(
                    seriesTranslator = SeriesTranslator()
                )
            ))
        }

        @Provides
        @ActivityScoped
        fun provideEpisodeUseCase(repository: com.okysoft.infra.repository.EpisodeRepository): EpisodeUseCase {
            return EpisodeUseCaseImpl(repository, EpisodeTranslator())
        }

        @Provides
        @ActivityScoped
        fun provideProgramUseCase(repository: com.okysoft.infra.repository.ProgramRepository): ProgramUseCase {
            return ProgramUseCaseImpl(repository, ProgramTranslator())
        }

    }

    @Module
    @InstallIn(FragmentComponent::class)
    class FragmentUseCaseModule {

        @Provides
        @FragmentScoped
        fun provideStaffUseCase(repository: com.okysoft.infra.repository.StaffRepository): StaffUseCase {
            return StaffUseCaseImpl(repository, StaffTranslator())
        }

        @Provides
        @FragmentScoped
        fun provideWorkUseCase(repository: WorkRepository): WorkUseCase {
            return WorkUseCaseImpl(repository, WorkTranslator())
        }


        @Provides
        @FragmentScoped
        fun provideRecordUseCase(repository: com.okysoft.infra.repository.RecordRepository): RecordUseCase {
            return RecordUseCaseImpl(repository, RecordTranslator())
        }


        @Provides
        @FragmentScoped
        fun provideReviewUseCase(repository: ReviewRepository): ReviewUseCase {
            return ReviewUseCaseImpl(repository, ReviewTranslator())
        }

    }
}