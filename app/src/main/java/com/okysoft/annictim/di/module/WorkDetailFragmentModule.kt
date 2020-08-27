package com.okysoft.annictim.di.module

//@InstallIn(FragmentComponent::class)
//@Module
//class WorkDetailFragmentModule {
//
//    @Provides
//    fun provideWorkDetailFragment(fragment: Fragment) : WorkDetailFragment {
//        return fragment as WorkDetailFragment
//    }
//
//    @Provides
//    fun providesWork(fragment: WorkDetailFragment): Work {
//        return fragment.work
//    }
//
//    @Provides
//    fun provideWorkViewModel(
//        factory: WorkViewModel.Factory,
//        target: WorkDetailFragment
//    ) = ViewModelProvider(target, factory).get(WorkViewModel::class.java)
//
//
//
//}