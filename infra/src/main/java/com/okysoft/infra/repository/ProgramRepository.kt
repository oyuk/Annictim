package com.okysoft.infra.repository

import com.okysoft.infra.response.ProgramsResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class ProgramRepository @Inject constructor(private val service: AnnictService.Program) {

    suspend fun get(requestParams: com.okysoft.data.ProgramRequestParams): ProgramsResponse {
        return service.get(requestParams.toParams())
    }

}