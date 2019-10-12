package com.okysoft.domain.usecase

import com.okysoft.domain.model.Review
import kotlinx.coroutines.Deferred

interface ReviewUseCase {

    fun get(workId: Int): Deferred<List<Review>>

}
