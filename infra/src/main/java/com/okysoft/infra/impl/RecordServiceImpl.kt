package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class RecordServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Record {

    private val client = retrofit.create(AnnictService.Record::class.java)

    override fun get(episodeId: Int): Deferred<com.okysoft.data.RecordsResponse> {
        return client.get(episodeId)
    }

}