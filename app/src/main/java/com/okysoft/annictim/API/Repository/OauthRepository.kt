package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.BuildConfig
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OauthRepository @Inject constructor(private val oauthService: AnnictService.Oauth) {
//
    fun getAccessToken(code: String): Single<String> {
        val requestModel = OauthRequestModel(
                clientId = BuildConfig.annictClientId,
                clientSecret = BuildConfig.annictClientKey,
                grantType = "authorization_code",
                redirectUri = "com.okysoft.annictim.oauth://callback",
                code = code
        )
        return oauthService.getAccessToken(requestModel)
                .map { it.accessToken }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}