package com.okysoft.annictim

import androidx.lifecycle.LiveData
import com.okysoft.annictim.api.model.response.User
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

class MeStore (
        private val authRepository: AuthRepository,
        dispatcher: ApplicationDispatcher
        ) {

    val isLoggedIn: Boolean
        get() = authRepository.getStoredAccessToken().isNotEmpty()

    val logout: LiveData<Unit> = dispatcher.logout
            .map { }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

    val me: Flowable<User> = dispatcher.getMe.map { it.me }

}