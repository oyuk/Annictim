package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.CastResponse
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: AnnictService.Cast) {

    fun get(requestParams: CastRequestParams): Deferred<CastResponse> {
        return service.get(requestParams.toParams())
    }

}