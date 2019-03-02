package com.okysoft.annictim.infra.api

import com.okysoft.annictim.infra.api.model.response.RecordsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class RecordServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Record {

    private val client = retrofit.create(AnnictService.Record::class.java)

    override fun get(episodeId: Int): Deferred<RecordsResponse> {
        return client.get(episodeId)
    }

}