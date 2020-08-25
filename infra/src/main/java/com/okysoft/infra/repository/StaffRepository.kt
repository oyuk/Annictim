package com.okysoft.infra.repository

import com.okysoft.infra.response.StaffsResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface StaffRepository {

    suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): Flow<StaffsResponse>

}