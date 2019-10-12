package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class WorkRepository @Inject constructor(private val service: AnnictService.Works) {

    fun get(requestParams: com.okysoft.data.WorkRequestParams): Deferred<com.okysoft.data.WorksResponse> {
        return service.get(requestParams.toParams())
    }

    fun me(requestParamModel: com.okysoft.data.WorkRequestParams): Deferred<com.okysoft.data.WorksResponse> {
        return service.me(requestParamModel.toParams())
    }

    fun request(requestParams: com.okysoft.data.WorkRequestParams, page: Int): Deferred<com.okysoft.data.WorksResponse> {
        return when (requestParams.type) {
            com.okysoft.data.WorkRequestParams.Type.Me -> me(requestParams.copy(page = page))
            com.okysoft.data.WorkRequestParams.Type.Works -> get(requestParams.copy(page = page))
        }
    }

}
