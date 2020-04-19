package com.okysoft.infra.repository

import com.okysoft.infra.response.StaffsResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class StaffRepository @Inject constructor(private val service: AnnictService.Staff) {

    suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): StaffsResponse {
        return service.get(requestParams.toParams())
    }

}