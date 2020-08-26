package com.okysoft.annictim.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.cast.CastsFragment
import com.okysoft.annictim.presentation.cast.CastsViewModel
import com.okysoft.data.CastRequestParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class CastsFragmentModule {

    @Provides
    fun provideCastsFragment(fragment: Fragment) : CastsFragment {
        return fragment as CastsFragment
    }

    @Provides
    fun providesCastRequestParams(fragment: CastsFragment): CastRequestParams {
        return fragment.castRequestParams
    }

    @Provides
    fun provideCastsViewModel(
        factory: CastsViewModel.Factory,
        target: CastsFragment
    ) = ViewModelProvider(target, factory).get(CastsViewModel::class.java)

}
