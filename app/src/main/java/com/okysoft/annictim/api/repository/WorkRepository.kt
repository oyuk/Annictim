package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.WorkRequestParams
import com.okysoft.annictim.api.model.response.WorksResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class WorkRepository @Inject constructor(private val service: AnnictService.Works) {

    fun get(requestParams: WorkRequestParams): Deferred<WorksResponse> {
        return service.get(requestParams.toParams())
    }

    fun me(requestParamModel: WorkRequestParams): Deferred<WorksResponse> {
        return service.me(requestParamModel.toParams())
    }

    fun request(requestParams: WorkRequestParams, page: Int): Deferred<WorksResponse> {
        return when (requestParams.type) {
            WorkRequestParams.Type.Me -> me(requestParams.copy(page = page))
            WorkRequestParams.Type.Works -> get(requestParams.copy(page = page))
        }
    }

}
