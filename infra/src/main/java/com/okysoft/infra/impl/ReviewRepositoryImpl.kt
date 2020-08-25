package com.okysoft.infra.impl

import com.okysoft.infra.response.ReviewsResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.ReviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(retrofit: Retrofit): ReviewRepository {

    private val client = retrofit.create(AnnictService.Review::class.java)

    override suspend fun get(workId: Int): Flow<ReviewsResponse> {
        return flow {
            emit(client.get(workId))
        }
            .flowOn(Dispatchers.IO)
    }

}