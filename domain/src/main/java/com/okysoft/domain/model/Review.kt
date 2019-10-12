package com.okysoft.domain.model

import com.okysoft.data.ReviewResponse

data class Review(
    val id: Int,
    val title: String,
    val body: String,
    val ratingAnimationState: String?,
    val ratingMusicState: String?,
    val ratingStoryState: String?,
    val ratingCharacterState: String?,
    val ratingOverallState: String?,
    val createdAt: String,
    val user: ReviewResponse.User
) {

    enum class Rating {
        animation, mutic, story, character, overall
    }

    internal fun ratingToNum(rating: String): Float {
        return when(rating) {
            "bad" -> 1f
            "average" -> 2f
            "good" -> 2f
            "great" -> 4f
            else -> 0f
        }
    }

    val hasRating: Boolean
        get() {
            return ratingAnimationState != null &&
                ratingMusicState != null &&
                ratingStoryState != null &&
                ratingCharacterState != null &&
                ratingOverallState != null
        }

}