package com.okysoft.domain.usecase

import com.okysoft.domain.model.Staff
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface StaffUseCase {

    suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): Flow<List<Staff>>

}
