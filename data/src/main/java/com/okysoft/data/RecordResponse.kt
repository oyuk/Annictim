package com.okysoft.data
import com.google.gson.annotations.SerializedName

data class RecordResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("comment") val comment: String?,
    @SerializedName("rating_state") val ratingState: String,
    @SerializedName("is_modified") val isModified: Boolean,
    @SerializedName("likes_count") val likesCount: Int,
    @SerializedName("comments_count") val commentsCount: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("user") val user: User
) {

    data class User(
        @SerializedName("id") val id: Int,
        @SerializedName("username") val username: String,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("url") val url: String,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("background_image_url") val backgroundImageUrl: String,
        @SerializedName("records_count") val recordsCount: Int,
        @SerializedName("created_at") val createdAt: String
    )

    val rating: Int = when(ratingState) {
        "bad" -> 0
        "average" -> 1
        "good" -> 2
        "great" -> 3
        else -> 0
    }

}