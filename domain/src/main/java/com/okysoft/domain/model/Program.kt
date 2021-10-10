package com.okysoft.domain.model

import com.okysoft.infra.response.EpisodeResponse
import com.okysoft.infra.response.WorkResponse

data class Program(
    val channel: Channel,
    val episode: EpisodeResponse?,
    val id: Int,
    val startedAt: String,
    val workResponse: WorkResponse
) {

    data class Channel(
        val id: Int,
        val name: String
    )

}