package com.okysoft.annictim.infra.api.model.response
import com.google.gson.annotations.SerializedName


data class Program(
    @SerializedName("channel")
    val channel: Channel,
    @SerializedName("episode")
    val episode: Episode,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_rebroadcast")
    val isRebroadcast: Boolean,
    @SerializedName("started_at")
    val startedAt: String,
    @SerializedName("work")
    val workResponse: WorkResponse
) {

    data class Channel(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

}