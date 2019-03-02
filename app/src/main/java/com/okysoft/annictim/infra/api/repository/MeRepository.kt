package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.request.WorkStatusRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MeRepository @Inject constructor(private val service: AnnictService.Me) {

    fun updateStatus(statusRequestParams: WorkStatusRequestParams): Deferred<Unit> {
        return service.status(statusRequestParams.toParams())
    }

}