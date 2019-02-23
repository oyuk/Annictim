package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.CastResponse
import com.okysoft.annictim.presentation.CastRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: AnnictService.Cast) {

    fun get(requestParams: CastRequestParams): Deferred<CastResponse> {
        return service.get(requestParams.toParams())
    }

}