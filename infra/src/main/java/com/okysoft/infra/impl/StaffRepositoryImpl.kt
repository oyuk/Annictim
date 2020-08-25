package com.okysoft.infra.impl

import com.okysoft.data.StaffRequestParams
import com.okysoft.infra.response.StaffsResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.StaffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class StaffRepositoryImpl @Inject constructor(retrofit: Retrofit): StaffRepository {

    private val client = retrofit.create(AnnictService.Staff::class.java)

    override suspend fun get(requestParams: StaffRequestParams): Flow<StaffsResponse> {
        return flow {
            emit(client.get(requestParams.toParams()))
        }
            .flowOn(Dispatchers.IO)
    }

}