package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Staff
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import kotlinx.coroutines.Deferred

interface StaffUseCase {

    fun get(requestParams: StaffRequestParams): Deferred<List<Staff>>

}
