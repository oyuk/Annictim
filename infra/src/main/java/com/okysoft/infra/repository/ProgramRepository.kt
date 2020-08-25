package com.okysoft.infra.repository

import com.okysoft.infra.response.ProgramsResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProgramRepository {

    fun get(requestParams: com.okysoft.data.ProgramRequestParams): Flow<ProgramsResponse>

}