package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.API.Model.Response.User
import com.okysoft.annictim.API.Repository.UserRepository
import com.okysoft.annictim.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class UserViewModel constructor(
        private val userRepository: UserRepository
): ViewModel() {

    class Factory @Inject constructor(
            private val userRepository: UserRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository) as T
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
