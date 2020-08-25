package com.okysoft.infra.impl

import com.okysoft.data.CastRequestParams
import com.okysoft.infra.response.CastsResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.CastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(retrofit: Retrofit): CastRepository {

    private val client = retrofit.create(AnnictService.Cast::class.java)

    override fun get(requestParams: CastRequestParams): Flow<CastsResponse> {
        return flow {
            emit(client.get(requestParams.toParams()))
        }
    }
}