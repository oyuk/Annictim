package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.EpisodesResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: AnnictService.Episode) {

    fun get(workId: Int): Deferred<EpisodesResponse> {
        return service.get(workId, "asc")
    }

}