package com.okysoft.infra.impl

import com.okysoft.infra.response.EpisodesResponse
import com.okysoft.infra.AnnictService
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Episode {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override suspend fun get(workId: Int, order: String): EpisodesResponse {
        return client.get(workId, order)
    }

}