package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Review
import com.okysoft.annictim.infra.api.repository.ReviewRepository
import com.okysoft.annictim.translator.ReviewTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class ReviewUseCaseImpl(
    private val repository: ReviewRepository,
    private val translator: ReviewTranslator
): ReviewUseCase {

    override fun get(workId: Int): Single<List<Review>> {
        return GlobalScope.rxSingle {
            val response = repository.get(workId).await()
            val models = response.reviews.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}