package com.okysoft.infra.repository

import com.okysoft.infra.response.EpisodesResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: AnnictService.Episode) {

    suspend fun get(workId: Int): EpisodesResponse {
        return service.get(workId, "asc")
    }

}