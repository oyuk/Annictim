package com.okysoft.annictim.API.Model.Response
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("background_image_url")
    val backgroundImageUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("followers_count")
    val followersCount: Int,
    @SerializedName("followings_count")
    val followingsCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("on_hold_count")
    val onHoldCount: Int,
    @SerializedName("records_count")
    val recordsCount: Int,
    @SerializedName("stop_watching_count")
    val stopWatchingCount: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("wanna_watch_count")
    val wannaWatchCount: Int,
    @SerializedName("watched_count")
    val watchedCount: Int,
    @SerializedName("watching_count")
    val watchingCount: Int
)