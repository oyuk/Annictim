package com.okysoft.annictim.presentation.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.User
import com.okysoft.annictim.usecase.UserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserViewModel constructor(
    private val userUseCase: UserUseCase,
    private val coroutineContext: CoroutineContext
): ViewModel() {

    class Factory @Inject constructor(
        private val userUseCase: UserUseCase,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userUseCase, coroutineContext) as T
        }
    }

    private val job = Job()
    var userId: Int = 0
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun fetch() {
         GlobalScope.launch(coroutineContext + job) {
             try {
                 val userResponse = userUseCase.get(userId).await()
                 val user = userResponse.first()
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
