package com.okysoft.annictim.presentation.user

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.domain.model.User
import com.okysoft.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {

    class Factory @Inject constructor(
        private val userUseCase: UserUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userUseCase) as T
        }
    }

    var userId: Int = 0
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun fetch() {
        viewModelScope.launch {
             try {
                 userUseCase.get(userId).collect {
                     val user = it.first()
                     _user.postValue(user)
                 }
             } catch (throwable: Throwable) {
                 Log.d("", throwable.toString())
             }
        }
    }

}
