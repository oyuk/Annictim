package com.okysoft.infra.repository

import com.okysoft.infra.response.ReviewsResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReviewRepository {

    suspend fun get(workId: Int): Flow<ReviewsResponse>

}