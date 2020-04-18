package com.okysoft.infra.repository

import android.util.Log
import com.okysoft.infra.AnnictService
import com.okysoft.infra.BuildConfig
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class OauthRepository @Inject constructor(private val oauthService: AnnictService.Oauth,
                                          private val CoroutineContext: CoroutineContext) {

    private val _accessToken = BehaviorProcessor.create<String>()
    val accessToken: Flowable<String> = _accessToken.share()

    fun getAccessToken(code: String) {
        val requestModel = com.okysoft.data.OauthRequestModel(
            clientId = BuildConfig.annictClientId,
            clientSecret = BuildConfig.annictClientKey,
            grantType = "authorization_code",
            redirectUri = "com.okysoft.annictim.oauth://callback",
            code = code
        )
        GlobalScope.launch(CoroutineContext) {
            try {
                val response = oauthService.getAccessToken(requestModel)
                _accessToken.onNext(response.accessToken)
            } catch (throwable: Throwable) {
                Log.e("", throwable.toString())
            }
        }
    }

}