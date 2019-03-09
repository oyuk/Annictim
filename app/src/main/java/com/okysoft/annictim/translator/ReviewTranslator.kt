package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Review
import com.okysoft.annictim.infra.api.model.response.ReviewResponse

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