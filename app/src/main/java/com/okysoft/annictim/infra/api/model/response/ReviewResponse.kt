package com.okysoft.annictim.infra.api.model.response
import com.google.gson.annotations.SerializedName

data class ReviewResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("body") val body: String,
        @SerializedName("rating_animation_state") val ratingAnimationState: String?,
        @SerializedName("rating_music_state") val ratingMusicState: String?,
        @SerializedName("rating_story_state") val ratingStoryState: String?,
        @SerializedName("rating_character_state") val ratingCharacterState: String?,
        @SerializedName("rating_overall_state") val ratingOverallState: String?,
        @SerializedName("likes_count") val likesCount: Int,
        @SerializedName("impressions_count") val impressionsCount: Int,
        @SerializedName("modified_at") val modifiedAt: Any,
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
}