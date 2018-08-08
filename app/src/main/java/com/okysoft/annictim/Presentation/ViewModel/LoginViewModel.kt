package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.API.Model.Response.OauthResponseModel
import com.okysoft.annictim.API.Repository.OauthRepository
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.BuildConfig
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val oauthRepository: OauthRepository,
        private val authRepository: AuthRepository): ViewModel() {

    private val getAccessToken = MutableLiveData<Uri>()
    val loginComplete: LiveData<Void> = MutableLiveData<Void>()

    init {
//       val fetchs = Transformations.switchMap(getAccessToken) {
//            return@switchMap fetch(it)
//        }
//        val a = Transformations.map(fetchs) {
//            saveAccessToken(it)
//            loginComplete.value = Unit
//        }
    }

    private fun saveAccessToken(responseModel: OauthResponseModel) {
        authRepository.putAccessToken(responseModel.accessToken)
    }

    private fun fetch(uri: Uri): LiveData<OauthResponseModel> {
        val code = uri.getQueryParameter("code")
        val requestModel = OauthRequestModel(
                clientID = BuildConfig.annictClientId,
                clientSecret = BuildConfig.annictClientKey,
                grandType = "authorization_code",
                redirectURL = "com.okysoft.annictim.oauth://callback",
                code = code
        )
        return oauthRepository.getAccessToken(requestModel)
    }

    fun fetchAuthToken(uri: Uri) {
        getAccessToken.value = uri
    }

}