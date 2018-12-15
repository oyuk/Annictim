package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.RecordsResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class RecordServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Record {

    private val client = retrofit.create(AnnictService.Record::class.java)

    override fun get(episodeId: Int): Single<RecordsResponse> {
        return client.get(episodeId)
    }

}