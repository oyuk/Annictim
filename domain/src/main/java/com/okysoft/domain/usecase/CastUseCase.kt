package com.okysoft.domain.usecase

import com.okysoft.domain.model.Cast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers

interface CastUseCase {

    suspend fun get(requestParams: com.okysoft.data.CastRequestParams, dispatcher: CoroutineDispatcher = Dispatchers.IO): List<Cast>

}
