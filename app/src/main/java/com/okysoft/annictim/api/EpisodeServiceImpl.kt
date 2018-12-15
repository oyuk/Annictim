package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.EpisodesResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Episode {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override fun get(workId: Int, order: String): Single<EpisodesResponse> {
        return client.get(workId, order)
    }

}