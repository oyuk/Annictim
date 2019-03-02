package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.RecordsResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class RecordRepository @Inject constructor(private val service: AnnictService.Record) {

    fun get(episodeId: Int): Deferred<RecordsResponse> {
        return service.get(episodeId)
    }

}