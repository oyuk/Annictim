package com.okysoft.annictim.di.module

import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.presentation.user.UserFragment
import com.okysoft.annictim.presentation.user.UserViewModel
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
        ) = ViewModelProvider(target, factory).get(UserViewModel::class.java)

    }

}