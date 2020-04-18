package com.okysoft.infra.repository

import com.okysoft.data.ReviewsResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val service: AnnictService.Review) {

    suspend fun get(workId: Int): com.okysoft.data.ReviewsResponse {
        return service.get(workId)
    }

}