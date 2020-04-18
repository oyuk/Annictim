package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ProgramRepository @Inject constructor(private val service: AnnictService.Program) {

    suspend fun get(requestParams: com.okysoft.data.ProgramRequestParams): com.okysoft.data.ProgramsResponse {
        return service.get(requestParams.toParams())
    }

}