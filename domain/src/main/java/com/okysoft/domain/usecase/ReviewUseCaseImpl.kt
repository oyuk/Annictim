package com.okysoft.domain.usecase

import com.okysoft.domain.model.Review
import com.okysoft.domain.translator.ReviewTranslator
import com.okysoft.infra.repository.ReviewRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReviewUseCaseImpl(
    private val repository: ReviewRepository,
    private val translator: ReviewTranslator
): ReviewUseCase {

    override suspend fun get(workId: Int): Flow<List<Review>> {
        return repository.get(workId)
            .map { response ->
                response.reviews.map { translator.translate(it) }
            }
    }
}
