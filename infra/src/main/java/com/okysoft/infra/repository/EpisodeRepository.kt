package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: AnnictService.Episode) {

    suspend fun get(workId: Int): com.okysoft.data.EpisodesResponse {
        return service.get(workId, "asc")
    }

}