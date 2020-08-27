package com.okysoft.annictim.di.module

import com.okysoft.annictim.presentation.record.RecordDispatcher
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class RecordsFragmentModule {

    @Provides
    @Reusable
    fun providesDispatcher(): RecordDispatcher {
        return RecordDispatcher()
    }
}
