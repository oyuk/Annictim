package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class StaffRepository @Inject constructor(private val service: AnnictService.Staff) {

    suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): com.okysoft.data.StaffsResponse {
        return service.get(requestParams.toParams())
    }

}