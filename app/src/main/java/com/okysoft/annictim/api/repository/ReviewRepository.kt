package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.ReviewsResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val service: AnnictService.Review) {

    fun get(workId: Int): Deferred<ReviewsResponse>{
        return service.get(workId)
    }

}