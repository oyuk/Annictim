package com.okysoft.annictim.Di.Module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.Presentation.Fragment.UserFragment
import com.okysoft.annictim.Presentation.ViewModel.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFragmentModule {

    @ContributesAndroidInjector(modules = [UserFragmentModule.InjectViewModel::class])
    abstract fun contributeUserFragment(): UserFragment

    @Module
    class InjectViewModel {

        @Provides
        fun provideWorkViewModel(
                factory: UserViewModel.Factory,
                target: UserFragment
        ) = ViewModelProviders.of(target, factory).get(UserViewModel::class.java)

    }

}