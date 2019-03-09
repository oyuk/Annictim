package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.StaffsResponse
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class StaffRepository @Inject constructor(private val service: AnnictService.Staff) {

    fun get(requestParams: StaffRequestParams): Deferred<StaffsResponse>{
        return service.get(requestParams.toParams())
    }

}