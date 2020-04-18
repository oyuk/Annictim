package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class RecordRepository @Inject constructor(private val service: AnnictService.Record) {

    suspend fun get(episodeId: Int): com.okysoft.data.RecordsResponse {
        return service.get(episodeId)
    }

}