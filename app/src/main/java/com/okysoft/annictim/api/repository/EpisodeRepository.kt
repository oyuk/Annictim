package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.EpisodesResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: AnnictService.Episode) {

    fun get(workId: Int): Deferred<EpisodesResponse> {
        return service.get(workId, "asc")
    }

}