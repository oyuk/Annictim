package com.okysoft.annictim.presentation.login

import android.net.Uri
import androidx.lifecycle.*
import com.okysoft.annictim.BuildConfig
import com.okysoft.annictim.extension.toLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val oauthRepository: com.okysoft.infra.repository.OauthRepository,
    private val authRepository: com.okysoft.infra.repository.AuthRepository): ViewModel() {

    private val _loginComplete =  MutableLiveData<Unit>()
    val loginComplete: LiveData<Unit> = _loginComplete

    private val _openLoginView = MutableLiveData<Uri>()
    val openLoginView: LiveData<Uri> = _openLoginView

    init {
        viewModelScope.launch {
            oauthRepository.accessToken.collect {
                saveAccessToken(it)
                _loginComplete.postValue(Unit)
            }
        }
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

}