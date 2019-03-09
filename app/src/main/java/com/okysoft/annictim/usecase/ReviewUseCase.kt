package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Review
import kotlinx.coroutines.Deferred

interface ReviewUseCase {

    fun get(workId: Int): Deferred<List<Review>>

}
