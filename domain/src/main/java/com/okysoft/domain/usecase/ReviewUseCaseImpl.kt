package com.okysoft.domain.usecase

import com.okysoft.domain.model.Review
import com.okysoft.domain.translator.ReviewTranslator
import com.okysoft.infra.repository.ReviewRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ReviewUseCaseImpl(
    private val repository: ReviewRepository,
    private val translator: ReviewTranslator
): ReviewUseCase {

    override fun get(workId: Int): Deferred<List<Review>> {
        return GlobalScope.async {
            val response = repository.get(workId)
            val models = response.reviews.map { translator.translate(it) }
            return@async models
        }
    }

}