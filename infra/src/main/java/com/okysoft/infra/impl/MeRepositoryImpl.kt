package com.okysoft.infra.impl

import com.okysoft.data.WorkStatusRequestParams
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.MeRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MeRepositoryImpl @Inject constructor(retrofit: Retrofit): MeRepository {

    private val client = retrofit.create(AnnictService.Me::class.java)

    override suspend fun updateStatus(statusRequestParams: WorkStatusRequestParams): Flow<Response<Unit>> {
        return flow {
            emit(client.status(statusRequestParams.toParams()))
        }
    }
}