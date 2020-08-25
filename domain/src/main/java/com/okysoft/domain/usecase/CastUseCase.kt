package com.okysoft.domain.usecase

import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Cast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

interface CastUseCase {

    suspend fun get(requestParams: CastRequestParams): Flow<List<Cast>>

}
