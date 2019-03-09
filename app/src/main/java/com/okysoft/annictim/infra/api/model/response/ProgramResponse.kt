package com.okysoft.annictim.infra.api.model.response
import com.google.gson.annotations.SerializedName


data class ProgramResponse(
    @SerializedName("channel")
    val channel: ChannelResponse,
    @SerializedName("episode")
    val episode: EpisodeResponse,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_rebroadcast")
    val isRebroadcast: Boolean,
    @SerializedName("started_at")
    val startedAt: String,
    @SerializedName("work")
    val workResponse: WorkResponse
) {

    data class ChannelResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

}