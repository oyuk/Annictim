package com.okysoft.annictim

import androidx.lifecycle.LiveData
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.infra.ApplicationDispatcher
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

class MeStore (
    private val authRepository: com.okysoft.infra.repository.AuthRepository,
    dispatcher: ApplicationDispatcher
        ) {

    val isLoggedIn: Boolean
        get() = authRepository.getStoredAccessToken().isNotEmpty()

    val logout: LiveData<Unit> = dispatcher.logout
            .map { }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

    val me: Flowable<com.okysoft.data.UserResponse> = dispatcher.getMe.map { it.me }

}