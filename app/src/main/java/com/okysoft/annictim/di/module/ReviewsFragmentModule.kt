package com.okysoft.annictim.di.module

//@InstallIn(FragmentComponent::class)
//@Module
//class ReviewsFragmentModule {
//
//    @Provides
//    fun provideReviewsFragment(fragment: Fragment) : ReviewsFragment {
//        return fragment as ReviewsFragment
//    }
//
//
//    @Provides
//    @Named("ReviewsFragment_WorkId")
//    fun providesWorkId(fragment: ReviewsFragment): Int {
//        return fragment.workId
//    }
//
//    @Provides
//    fun provideReviewsViewModel(
//        factory: ReviewsViewModel.Factory,
//        target: ReviewsFragment
//    ) = ViewModelProvider(target, factory).get(ReviewsViewModel::class.java)
//
//}
