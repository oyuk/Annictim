package com.okysoft.infra.repository

import com.okysoft.data.WorkStatusRequestParams
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class MeRepository @Inject constructor(private val service: AnnictService.Me) {

    fun updateStatus(statusRequestParams: WorkStatusRequestParams): Deferred<Response<Unit>> {
        return service.status(statusRequestParams.toParams())
    }

}