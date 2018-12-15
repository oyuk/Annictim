package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.ReviewsResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ReviewServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Review {

    private val client = retrofit.create(AnnictService.Review::class.java)

    override fun get(workId: Int): Single<ReviewsResponse> {
        return client.get(workId)
    }

}