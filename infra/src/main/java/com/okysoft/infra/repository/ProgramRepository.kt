package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ProgramRepository @Inject constructor(private val service: AnnictService.Program) {

    fun get(requestParams: com.okysoft.data.ProgramRequestParams): Deferred<com.okysoft.data.ProgramsResponse> {
        return service.get(requestParams.toParams())
    }

}