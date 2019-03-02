package com.okysoft.annictim

import androidx.lifecycle.LiveData
import com.okysoft.annictim.infra.api.model.response.User
import com.okysoft.annictim.infra.api.repository.AuthRepository
import com.okysoft.annictim.application.ApplicationDispatcher
import com.okysoft.annictim.extension.toLiveData
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