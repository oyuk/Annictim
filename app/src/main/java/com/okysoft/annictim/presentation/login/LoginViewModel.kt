package com.okysoft.annictim.presentation.login

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okysoft.annictim.BuildConfig
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val oauthRepository: com.okysoft.infra.repository.OauthRepository,
    private val authRepository: com.okysoft.infra.repository.AuthRepository): ViewModel() {

    private val _loginComplete =  MutableLiveData<Unit>()
    val loginComplete: LiveData<Unit> = _loginComplete

    private val _openLoginView = MutableLiveData<Uri>()
    val openLoginView: LiveData<Uri> = _openLoginView

    val showLoading = mutableStateOf(false)

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

    fun openOauthLoginView() {
        val clientID = BuildConfig.annictClientId
        val baseURL = "https://api.annict.com"
        val params = "/oauth/authorize?client_id=${clientID}&redirect_uri=com.okysoft.annictim.oauth://callback&response_type=code&scope=read+write"
        val uri = Uri.parse(baseURL + params)
        _openLoginView.postValue(uri)
    }

}