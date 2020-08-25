package com.okysoft.infra.repository

import android.util.Log
import com.okysoft.infra.AnnictService
import com.okysoft.infra.BuildConfig
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface OauthRepository {
    val accessToken: Flow<String>
    fun getAccessToken(code: String)
}