package com.okysoft.annictim.API

import com.okysoft.annictim.API.Model.Response.Episode
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject


class EpisodeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Episode {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    override fun get(workId: Int): Single<List<Episode>> {
        return client.get(workId)
    }

}