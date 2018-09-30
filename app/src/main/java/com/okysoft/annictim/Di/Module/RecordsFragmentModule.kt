package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.Presentation.Fragment.RecordsFragment
import com.okysoft.annictim.Presentation.Fragment.ReviewsFragment
import com.okysoft.annictim.Presentation.RecordDispatcher
import com.okysoft.annictim.Presentation.ViewModel.RecordsViewModel
import com.okysoft.annictim.Presentation.ViewModel.ReviewsViewModel
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
