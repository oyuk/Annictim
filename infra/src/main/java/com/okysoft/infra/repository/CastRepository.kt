package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: AnnictService.Cast) {

    suspend fun get(requestParams: com.okysoft.data.CastRequestParams): com.okysoft.data.CastResponse {
        return service.get(requestParams.toParams())
    }

}