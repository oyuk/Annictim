package com.okysoft.infra.impl

import com.okysoft.infra.response.RecordsResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(retrofit: Retrofit): RecordRepository {

    private val client = retrofit.create(AnnictService.Record::class.java)

    override fun get(episodeId: Int): Flow<RecordsResponse> {
        return flow {
            emit(client.get(episodeId))
        }
    }
}