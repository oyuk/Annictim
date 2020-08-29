package com.okysoft.infra.repository

import com.okysoft.infra.fragment.Work
import com.okysoft.infra.response.WorksResponse
import kotlinx.coroutines.flow.Flow

interface WorkRepository {

    suspend fun get(requestParams: com.okysoft.data.WorkRequestParams): Flow<WorksResponse>

    suspend fun me(requestParamModel: com.okysoft.data.WorkRequestParams): Flow<WorksResponse>

    suspend fun request(requestParams: com.okysoft.data.WorkRequestParams, page: Int): Flow<WorksResponse>

    fun getWork(id: Int): Flow<Work>

}
