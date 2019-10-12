package com.okysoft.domain.usecase

import com.okysoft.domain.model.Staff
import kotlinx.coroutines.Deferred

interface StaffUseCase {

    fun get(requestParams: com.okysoft.data.StaffRequestParams): Deferred<List<Staff>>

}
