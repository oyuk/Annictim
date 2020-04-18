package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class CastServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Cast {

    private val client = retrofit.create(AnnictService.Cast::class.java)

    override suspend fun get(query: Map<String, String>): com.okysoft.data.CastResponse {
        return client.get(query)
    }

}