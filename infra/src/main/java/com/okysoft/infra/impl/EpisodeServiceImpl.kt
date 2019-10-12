package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Episode {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override fun get(workId: Int, order: String): Deferred<com.okysoft.data.EpisodesResponse> {
        return client.get(workId, order)
    }

}