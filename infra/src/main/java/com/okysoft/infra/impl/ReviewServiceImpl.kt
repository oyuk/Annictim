package com.okysoft.infra.impl

import com.okysoft.infra.response.ReviewsResponse
import com.okysoft.infra.AnnictService
import retrofit2.Retrofit
import javax.inject.Inject

class ReviewServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Review {

    private val client = retrofit.create(AnnictService.Review::class.java)

    override suspend fun get(workId: Int): ReviewsResponse {
        return client.get(workId)
    }

}