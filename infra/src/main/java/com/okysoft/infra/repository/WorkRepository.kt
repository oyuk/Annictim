package com.okysoft.infra.repository

import com.okysoft.infra.response.WorksResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class WorkRepository @Inject constructor(private val service: AnnictService.Works) {

    suspend fun get(requestParams: com.okysoft.data.WorkRequestParams): WorksResponse {
        return service.get(requestParams.toParams())
    }

    suspend fun me(requestParamModel: com.okysoft.data.WorkRequestParams): WorksResponse {
        return service.me(requestParamModel.toParams())
    }

    suspend fun request(requestParams: com.okysoft.data.WorkRequestParams, page: Int): WorksResponse {
        return when (requestParams.type) {
            com.okysoft.data.WorkRequestParams.Type.Me -> me(requestParams.copy(page = page))
            com.okysoft.data.WorkRequestParams.Type.Works -> get(requestParams.copy(page = page))
        }
    }

}
