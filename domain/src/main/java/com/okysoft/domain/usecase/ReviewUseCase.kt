package com.okysoft.domain.usecase

import com.okysoft.domain.model.Review
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface ReviewUseCase {

    suspend fun get(workId: Int): Flow<List<Review>>

}
