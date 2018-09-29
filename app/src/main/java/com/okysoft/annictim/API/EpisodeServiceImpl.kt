package com.okysoft.annictim.API

import com.okysoft.annictim.API.Model.Response.EpisodesResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Episode {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override fun get(workId: Int, order: String): Single<EpisodesResponse> {
        return client.get(workId, order)
    }

}