package com.okysoft.domain.model

import com.okysoft.data.EpisodeResponse
import com.okysoft.data.WorkResponse

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