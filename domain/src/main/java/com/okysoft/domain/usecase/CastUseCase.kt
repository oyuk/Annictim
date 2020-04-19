package com.okysoft.domain.usecase

import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Cast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CastUseCase {

    suspend fun get(requestParams: CastRequestParams, dispatcher: CoroutineDispatcher = Dispatchers.IO): List<Cast>

}
