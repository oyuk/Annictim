package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.ProgramsResponse
import com.okysoft.annictim.infra.api.model.request.ProgramRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ProgramRepository @Inject constructor(private val service: AnnictService.Program) {

    fun get(requestParams: ProgramRequestParams): Deferred<ProgramsResponse> {
        return service.get(requestParams.toParams())
    }

}