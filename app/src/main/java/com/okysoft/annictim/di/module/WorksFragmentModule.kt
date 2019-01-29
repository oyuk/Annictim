package com.okysoft.annictim.di.module

import android.arch.lifecycle.ViewModelProviders
import com.okysoft.annictim.api.model.WorksRequestParamModel
import com.okysoft.annictim.presentation.fragment.WorksFragment
import com.okysoft.annictim.presentation.viewModel.WorksViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class WorksFragmentModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(WorksViewModel::class)
//    abstract fun bindWorksViewModel(viewModel: WorksViewModel): ViewModel

//    @Binds
//    abstract fun bindViewModelFactory(factory: WorksViewModel.Factory): WorksViewModel.Factory

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun contributeWorksFragment(): WorksFragment

    @Module
    class InjectViewModel {

//        @Module
//        companion object {
//
//            @JvmStatic
//       chastRepository:      @Provides
//            fun providesWorkTerm(fragment: WorksFragment): String {
//                return fragment.workTerm
//            }
//
//        }

        @Provides
        fun providesWorksRequestParamModel(fragment: WorksFragment): WorksRequestParamModel {
            return fragment.worksRequestParamModel
        }

        @Provides
        fun provideWorksViewModel(
                factory: WorksViewModel.Factory,
                target: WorksFragment
        ) = ViewModelProviders.of(target, factory).get(WorksViewModel::class.java)

    }

//    @Module
//    class ProvideViewModel {
//
//        @Provides
//        @IntoMap
//        @ViewModelKey(WorksViewModel::class)
//        fun provideWorksViewModel(repository: WorkRepository, workTerm: String): ViewModel {
//            return WorksViewModel(repository, workTerm)
//        }
//
//    }

//    @Module
//    companion object {
//
//        @JvmStatic
//        @Provides
//        @Named("workTerm")
//        fun providesWorkTerm(fragment: WorksFragment): String {
//            return fragment.workTerm
//        }

//        @JvmStatic
//        @Provides
//        fun providesWorksViewModel(repository: WorkRepository, workTerm: String): WorksViewModel {
//            return WorksViewModel(repository, workTerm)
//        }

//    }

}
