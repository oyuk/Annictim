package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Review
import com.okysoft.annictim.infra.api.repository.ReviewRepository
import com.okysoft.annictim.translator.ReviewTranslator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ReviewUseCaseImpl(
    private val repository: ReviewRepository,
    private val translator: ReviewTranslator
): ReviewUseCase {

    override fun get(workId: Int): Deferred<List<Review>> {
        return GlobalScope.async {
            val response = repository.get(workId).await()
            val models = response.reviews.map { translator.translate(it) }
            return@async models
        }
    }

}