package com.okysoft.infra.impl

import com.okysoft.data.ProgramRequestParams
import com.okysoft.infra.response.ProgramsResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.ProgramRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class ProgramRepositoryImpl @Inject constructor(retrofit: Retrofit): ProgramRepository {

    private val retrofitClient = retrofit.create(AnnictService.Program::class.java)

    override fun get(requestParams: ProgramRequestParams): Flow<ProgramsResponse> {
        return flow {
            emit(retrofitClient.get(requestParams.toParams()))
        }
    }
}