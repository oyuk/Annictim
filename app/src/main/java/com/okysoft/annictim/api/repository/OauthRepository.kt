package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.request.OauthRequestModel
import com.okysoft.annictim.BuildConfig
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OauthRepository @Inject constructor(private val oauthService: AnnictService.Oauth) {

    private val _accessToken = BehaviorProcessor.create<String>()
    val accessToken: Flowable<String> = _accessToken.share()

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
                .doOnSuccess {
                    _accessToken.onNext(it)
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}