package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.ProgramsResponse
import com.okysoft.annictim.presentation.ProgramRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ProgramRepository @Inject constructor(private val service: AnnictService.Program) {

    fun get(requestParams: ProgramRequestParams): Deferred<ProgramsResponse> {
        return service.get(requestParams.toParams())
    }

}