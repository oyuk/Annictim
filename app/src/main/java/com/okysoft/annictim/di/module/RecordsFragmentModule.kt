package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProviders
import com.okysoft.annictim.presentation.fragment.RecordsFragment
import com.okysoft.annictim.presentation.fragment.ReviewsFragment
import com.okysoft.annictim.presentation.RecordDispatcher
import com.okysoft.annictim.presentation.viewModel.RecordsViewModel
import com.okysoft.annictim.presentation.viewModel.ReviewsViewModel
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.ContributesAndroidInjector

@Module
abstract class RecordsFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class, InjectDispatcher::class])
    abstract fun contributeRecordsFragment(): RecordsFragment

    @Module
    class InjectViewModel {

        @Provides
        fun providesEpisodeId(fragment: RecordsFragment): Int {
            return fragment.episodeId
        }

        @Provides
        fun provideReviewsViewModel(
                factory: RecordsViewModel.Factory,
                target: ReviewsFragment
        ) = ViewModelProviders.of(target, factory).get(ReviewsViewModel::class.java)

    }

    @Module
    class InjectDispatcher {

        @Provides
        @Reusable
        fun providesDispatcher(): RecordDispatcher {
            return RecordDispatcher()
        }

    }

}
