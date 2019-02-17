package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProviders
import com.okysoft.annictim.presentation.fragment.UserFragment
import com.okysoft.annictim.presentation.viewModel.UserViewModel
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