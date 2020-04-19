package com.okysoft.infra.repository

import com.okysoft.infra.response.ReviewsResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val service: AnnictService.Review) {

    suspend fun get(workId: Int): ReviewsResponse {
        return service.get(workId)
    }

}