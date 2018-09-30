package com.okysoft.annictim

import android.arch.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers

class MeStore(
        private val authRepository: AuthRepository,
        dispatcher: ApplicationDispatcher
        ) {

    val isLoggedIn: Boolean
        get() = authRepository.getStoredAccessToken().isNotEmpty()

    val logout: LiveData<Unit> = dispatcher.logout
            .map { }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

}