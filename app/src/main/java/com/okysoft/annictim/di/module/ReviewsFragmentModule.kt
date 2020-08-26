package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.review.ReviewsFragment
import com.okysoft.annictim.presentation.review.ReviewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@InstallIn(FragmentComponent::class)
@Module
class ReviewsFragmentModule {

    @Provides
    fun provideReviewsFragment(fragment: Fragment) : ReviewsFragment {
        return fragment as ReviewsFragment
    }


    @Provides
    @Named("ReviewsFragment_WorkId")
    fun providesWorkId(fragment: ReviewsFragment): Int {
        return fragment.workId
    }

    @Provides
    fun provideReviewsViewModel(
        factory: ReviewsViewModel.Factory,
        target: ReviewsFragment
    ) = ViewModelProvider(target, factory).get(ReviewsViewModel::class.java)

}
