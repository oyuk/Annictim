package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.BuildConfig

class LoginViewModel: ViewModel() {

//    private val repository: OauthRepository()

    fun fetchAuthToken(uri: Uri) {
        val code = uri.getQueryParameter("code")
        val requestModel = OauthRequestModel(
                clientID = BuildConfig.annictClientId,
                clientSecret = BuildConfig.annictClientKey,
                grandType = "authorization_code",
                redirectURL = "com.okysoft.annictim.oauth://callback",
                code = code
        )

    }

}