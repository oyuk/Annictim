package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.Presentation.ReviewsFragment
import com.okysoft.annictim.Presentation.ViewModel.ReviewsViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReviewsFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeReviewsFragment(): ReviewsFragment

    @Module
    class InjectViewModel {

        @Provides
        fun providesWorkId(fragment: ReviewsFragment): Int {
            return fragment.workId
        }

        @Provides
        fun provideReviewsViewModel(
                factory: ReviewsViewModel.Factory,
                target: ReviewsFragment
        ) = ViewModelProviders.of(target, factory).get(ReviewsViewModel::class.java)

    }

}
