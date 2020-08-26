package com.okysoft.annictim.presentation.launch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okysoft.annictim.MeStore

class LaunchViewModel @ViewModelInject constructor(meStore: MeStore): ViewModel() {

//    class Factory @Inject constructor(
//            private val meStore: MeStore
//    ) : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return LaunchViewModel(meStore) as T
//        }
//    }

    enum class TransitionType {
        Login, Main
    }

    private val _transition = MutableLiveData<TransitionType>()
    val transition: LiveData<TransitionType> = _transition

    init {
        val transitionType = if (meStore.isLoggedIn) TransitionType.Main else TransitionType.Login
        _transition.postValue(transitionType)
    }

}