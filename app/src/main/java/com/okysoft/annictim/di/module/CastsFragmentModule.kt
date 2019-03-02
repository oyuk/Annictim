package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProviders
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import com.okysoft.annictim.presentation.cast.CastsFragment
import com.okysoft.annictim.presentation.cast.CastsViewModel
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
        ) = ViewModelProviders.of(target, factory).get(CastsViewModel::class.java)

    }

}
