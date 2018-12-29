package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.BuildConfig
import com.okysoft.annictim.api.repository.OauthRepository
import com.okysoft.annictim.toLiveData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val oauthRepository: OauthRepository,
        private val authRepository: AuthRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val loginComplete: LiveData<Unit> by lazy {
        oauthRepository.accessToken
                .map {  }
                .toLiveData()
    }

    private val _openLoginView = MutableLiveData<Uri>()
    val openLoginView:LiveData<Uri> = _openLoginView

    init {
        oauthRepository.accessToken
                .subscribeBy {
                    saveAccessToken(it)
                }.addTo(compositeDisposable)

    }

    private fun saveAccessToken(accessToken: String) {
        authRepository.putAccessToken(accessToken)
    }

    private fun fetchAccessToken(uri: Uri): Single<String> {
        val code = uri.getQueryParameter("code")
        return oauthRepository.getAccessToken(code)
    }

    fun fetch(uri: Uri) {
        fetchAccessToken(uri)
                .subscribe()
                .addTo(compositeDisposable)
    }

    fun onCreate() {
        val clientID = BuildConfig.annictClientId
        val baseURL = "https://api.annict.com"
        val params = "/oauth/authorize?client_id=${clientID}&redirect_uri=com.okysoft.annictim.oauth://callback&response_type=code&scope=read+write"
        val uri = Uri.parse(baseURL + params)
        _openLoginView.postValue(uri)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}