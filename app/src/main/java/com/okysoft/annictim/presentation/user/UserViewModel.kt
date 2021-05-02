package com.okysoft.annictim.presentation.user

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.annictim.presentation.works.WorksViewModel
import com.okysoft.data.WorkRequestParams
import com.okysoft.domain.model.User
import com.okysoft.domain.usecase.UserUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @AssistedInject constructor(
    private val userUseCase: UserUseCase,
    @Assisted private val userId: Int
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(userId: Int): UserViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            userId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(userId) as T
            }
        }
    }

    private val _user = MutableStateFlow<User?>(null)
    val user: LiveData<User> = _user.filterNotNull().asLiveData()

    fun fetch() {
        viewModelScope.launch {
             try {
                 userUseCase.get(userId).collect {
                     val user = it.first()
                     _user.value = user
                 }
             } catch (throwable: Throwable) {
                 Log.d("", throwable.toString())
             }
        }
    }

}
