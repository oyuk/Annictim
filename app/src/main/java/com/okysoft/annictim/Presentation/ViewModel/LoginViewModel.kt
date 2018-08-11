package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.okysoft.annictim.API.Repository.OauthRepository
import com.okysoft.annictim.AuthRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val oauthRepository: OauthRepository,
        private val authRepository: AuthRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val loginComplete = MutableLiveData<Unit>()

    private fun saveAccessToken(accessToken: String) {
        authRepository.putAccessToken(accessToken)
    }

    private fun fetchAccessToken(uri: Uri): Single<String> {
        val code = uri.getQueryParameter("code")
        return oauthRepository.getAccessToken(code)
    }

    fun fetc(uri: Uri) {
        fetchAccessToken(uri)
                .subscribeBy {
                    saveAccessToken(it)
                    loginComplete.value = Unit
                }.addTo(compositeDisposable)
    }

}