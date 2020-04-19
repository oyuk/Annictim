package com.okysoft.infra.repository

import com.okysoft.data.CastRequestParams
import com.okysoft.infra.response.CastsResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: AnnictService.Cast) {

    suspend fun get(requestParams: CastRequestParams): CastsResponse {
        return service.get(requestParams.toParams())
    }

}