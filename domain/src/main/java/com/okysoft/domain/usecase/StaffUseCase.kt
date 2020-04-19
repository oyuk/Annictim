package com.okysoft.domain.usecase

import com.okysoft.domain.model.Staff
import kotlinx.coroutines.Deferred

interface StaffUseCase {

    suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): List<Staff>

}
