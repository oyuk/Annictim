package com.okysoft.infra.repository

import com.okysoft.infra.response.RecordsResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class RecordRepository @Inject constructor(private val service: AnnictService.Record) {

    suspend fun get(episodeId: Int): RecordsResponse {
        return service.get(episodeId)
    }

}