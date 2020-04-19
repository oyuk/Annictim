package com.okysoft.domain.usecase

import com.okysoft.domain.model.Review
import kotlinx.coroutines.Deferred

interface ReviewUseCase {

    suspend fun get(workId: Int): List<Review>

}
