package com.okysoft.annictim.di.module

import com.okysoft.annictim.presentation.search.SearchFragment
import com.okysoft.annictim.presentation.search.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeSearchFragment(): SearchFragment

    @Module
    class InjectViewModel {

        @Provides
        fun provideSearchViewModel(): SearchViewModel = SearchViewModel()

    }
}