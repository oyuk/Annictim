package com.okysoft.annictim.domain

import com.okysoft.annictim.infra.api.model.response.EpisodeResponse
import com.okysoft.annictim.infra.api.model.response.WorkResponse

data class Program(
    val channel: Channel,
    val episode: EpisodeResponse,
    val id: Int,
    val startedAt: String,
    val workResponse: WorkResponse
) {

    data class Channel(
        val id: Int,
        val name: String
    )

}