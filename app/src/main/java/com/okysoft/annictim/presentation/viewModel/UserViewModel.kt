package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.api.model.response.User
import com.okysoft.annictim.api.repository.UserRepository
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class UserViewModel constructor(
        private val userRepository: UserRepository,
        private val authRepository: AuthRepository
): ViewModel() {

    class Factory @Inject constructor(
            private val userRepository: UserRepository,
            private val authRepository: AuthRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository, authRepository) as T
        }
    }

    var userId: Int = 0
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    private val compositeDisposable = CompositeDisposable()

    fun fetch() {
        userRepository.get(userId)
                .subscribe(
                        {
                            when (it) {
                                is Result.Success -> {
                                    _user.postValue(it.data)
                                }
                                is Result.Failure -> {
                                    Log.d("", it.throwable.toString())
                                }
                            }
                        },
                        { throwable ->
                            Log.d("", throwable.toString())
                        }
                )
                .addTo(compositeDisposable)
    }


}
