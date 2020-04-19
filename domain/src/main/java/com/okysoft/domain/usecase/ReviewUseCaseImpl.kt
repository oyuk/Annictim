package com.okysoft.domain.usecase

import com.okysoft.domain.model.Review
import com.okysoft.domain.translator.ReviewTranslator
import com.okysoft.infra.repository.ReviewRepository
import kotlinx.coroutines.*

class ReviewUseCaseImpl(
    private val repository: ReviewRepository,
    private val translator: ReviewTranslator
): ReviewUseCase {

    override suspend fun get(workId: Int): List<Review> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(workId)
            val models = response.reviews.map { translator.translate(it) }
            return@withContext models
        }
    }

}