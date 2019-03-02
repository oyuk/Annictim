package com.okysoft.annictim.infra.api.repository

import android.util.Log
import com.okysoft.annictim.BuildConfig
import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.request.OauthRequestModel
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
        val requestModel = OauthRequestModel(
                clientId = BuildConfig.annictClientId,
                clientSecret = BuildConfig.annictClientKey,
                grantType = "authorization_code",
                redirectUri = "com.okysoft.annictim.oauth://callback",
                code = code
        )
        GlobalScope.launch(CoroutineContext) {
            try {
                val response = oauthService.getAccessToken(requestModel).await()
                _accessToken.onNext(response.accessToken)
            } catch (throwable: Throwable) {
                Log.e("", throwable.toString())
            }
        }
    }

}