package com.okysoft.infra.impl

import com.okysoft.infra.response.EpisodesResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeRepositoryImpl @Inject constructor(retrofit: Retrofit): EpisodeRepository {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override suspend fun get(workId: Int, order: String): Flow<EpisodesResponse> {
        return flow {
            val response = client.get(workId, order)
            emit(response)
        }
            .flowOn(Dispatchers.IO)
    }

}