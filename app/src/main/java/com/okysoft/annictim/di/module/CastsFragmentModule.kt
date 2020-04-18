package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.cast.CastsFragment
import com.okysoft.annictim.presentation.cast.CastsViewModel
import com.okysoft.data.CastRequestParams
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class CastsFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeCastsFragment(): CastsFragment

    @Module
    class InjectViewModel {

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

}
