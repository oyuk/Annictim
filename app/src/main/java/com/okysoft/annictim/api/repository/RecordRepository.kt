package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.RecordsResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class RecordRepository @Inject constructor(private val service: AnnictService.Record) {

    fun get(episodeId: Int): Deferred<RecordsResponse> {
        return service.get(episodeId)
    }

}