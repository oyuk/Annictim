package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: AnnictService.Cast) {

    fun get(requestParams: com.okysoft.data.CastRequestParams): Deferred<com.okysoft.data.CastResponse> {
        return service.get(requestParams.toParams())
    }

}