package com.okysoft.domain.translator

import com.okysoft.infra.response.ReviewResponse
import com.okysoft.domain.model.Review

class ReviewTranslator: Translator<ReviewResponse, Review> {

    override fun translate(response: ReviewResponse): Review {
        return Review(
            id = response.id,
            title = response.title,
            body = response.body,
            ratingAnimationState = response.ratingAnimationState,
            ratingMusicState = response.ratingMusicState,
            ratingStoryState = response.ratingStoryState,
            ratingCharacterState = response.ratingCharacterState,
            ratingOverallState = response.ratingOverallState,
            createdAt = response.createdAt,
            user = response.user
        )
    }

}