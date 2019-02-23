package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.ReviewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class ReviewServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Review {

    private val client = retrofit.create(AnnictService.Review::class.java)

    override fun get(workId: Int): Deferred<ReviewsResponse> {
        return client.get(workId)
    }

}