package com.okysoft.infra.impl

import android.util.Log
import com.okysoft.infra.AnnictService
import com.okysoft.infra.BuildConfig
import com.okysoft.infra.repository.OauthRepository
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OauthRepositoryImpl(private val oauthService: AnnictService.Oauth,
                          private val CoroutineContext: CoroutineContext): OauthRepository {

    private val _accessToken = Channel<String>(Channel.CONFLATED)
    override val accessToken: Flow<String> = _accessToken.receiveAsFlow()

    override fun getAccessToken(code: String) {
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
                _accessToken.send(response.accessToken)
            } catch (throwable: Throwable) {
                Log.e("", throwable.toString())
                _accessToken.close(throwable)
            }
        }
    }

}