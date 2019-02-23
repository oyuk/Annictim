package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.StaffResponse
import com.okysoft.annictim.presentation.StaffRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class StaffRepository @Inject constructor(private val service: AnnictService.Staff) {

    fun get(requestParams: StaffRequestParams): Deferred<StaffResponse>{
        return service.get(requestParams.toParams())
    }

}