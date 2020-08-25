package com.okysoft.infra.repository

import com.okysoft.infra.response.WorksResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WorkRepository {

    suspend fun get(requestParams: com.okysoft.data.WorkRequestParams): Flow<WorksResponse>

    suspend fun me(requestParamModel: com.okysoft.data.WorkRequestParams): Flow<WorksResponse>

    suspend fun request(requestParams: com.okysoft.data.WorkRequestParams, page: Int): Flow<WorksResponse>

}
