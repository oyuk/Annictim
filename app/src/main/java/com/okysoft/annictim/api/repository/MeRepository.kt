package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.request.WorkStatusRequestParams
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MeRepository @Inject constructor(private val service: AnnictService.Me) {

    fun updateStatus(statusRequestParams: WorkStatusRequestParams): Deferred<Unit> {
        return service.status(statusRequestParams.toParams())
    }

}