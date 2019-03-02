package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.ReviewsResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val service: AnnictService.Review) {

    fun get(workId: Int): Deferred<ReviewsResponse>{
        return service.get(workId)
    }

}