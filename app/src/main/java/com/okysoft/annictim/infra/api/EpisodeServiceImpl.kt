package com.okysoft.annictim.infra.api

import com.okysoft.annictim.infra.api.model.response.EpisodesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Episode {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override fun get(workId: Int, order: String): Deferred<EpisodesResponse> {
        return client.get(workId, order)
    }

}