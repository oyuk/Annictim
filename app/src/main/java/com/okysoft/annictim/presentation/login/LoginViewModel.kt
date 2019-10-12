package com.okysoft.annictim.presentation.login

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okysoft.annictim.BuildConfig
import com.okysoft.annictim.extension.toLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val oauthRepository: com.okysoft.infra.repository.OauthRepository,
    private val authRepository: com.okysoft.infra.repository.AuthRepository): ViewModel() {

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

    private fun fetchAccessToken(uri: Uri) {
        val code = uri.getQueryParameter("code")
        code?.let { oauthRepository.getAccessToken(it) }
    }

    fun fetch(uri: Uri) {
        fetchAccessToken(uri)
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