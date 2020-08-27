package com.okysoft.annictim.di.module

//@InstallIn(FragmentComponent::class)
//@Module
//class CastsFragmentModule {
//
//    @Provides
//    fun provideCastsFragment(fragment: Fragment) : CastsFragment {
//        return fragment as CastsFragment
//    }
//
//    @Provides
//    fun providesCastRequestParams(fragment: CastsFragment): CastRequestParams {
//        return fragment.castRequestParams
//    }
//
//    @Provides
//    fun provideCastsViewModel(
//        factory: CastsViewModel.Factory,
//        target: CastsFragment
//    ) = ViewModelProvider(target, factory).get(CastsViewModel::class.java)
//
//}
