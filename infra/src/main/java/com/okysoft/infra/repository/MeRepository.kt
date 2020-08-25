package com.okysoft.infra.repository

import com.okysoft.data.WorkStatusRequestParams
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

interface MeRepository {

    suspend fun updateStatus(statusRequestParams: WorkStatusRequestParams): Flow<Response<Unit>>

}