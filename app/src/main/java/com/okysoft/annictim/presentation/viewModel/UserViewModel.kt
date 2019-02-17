package com.okysoft.annictim.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.api.model.response.User
import com.okysoft.annictim.api.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserViewModel constructor(
        private val userRepository: UserRepository,
        private val authRepository: AuthRepository,
        private val coroutineContext: CoroutineContext
): ViewModel() {

    class Factory @Inject constructor(
            private val userRepository: UserRepository,
            private val authRepository: AuthRepository,
            private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository, authRepository, coroutineContext) as T
        }
    }

    private val job = Job()
    var userId: Int = 0
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun fetch() {
         GlobalScope.launch(coroutineContext + job) {
             try {
                 val userResponse = userRepository.get(userId).await()
                 val user = userResponse.users.first()
                 _user.postValue(user)
             } catch (throwable: Throwable) {
                 Log.d("", throwable.toString())
             }
        }
    }

    fun onClear() {
        job.cancel()
    }

}
