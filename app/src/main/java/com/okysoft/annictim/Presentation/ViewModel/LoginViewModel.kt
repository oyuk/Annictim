package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.API.Repository.OauthRepository
import com.okysoft.annictim.BuildConfig
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: OauthRepository): ViewModel() {

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