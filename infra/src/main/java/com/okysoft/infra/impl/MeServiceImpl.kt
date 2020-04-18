package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Me {

    private val client = retrofit.create(AnnictService.Me::class.java)

    override suspend fun status(query: Map<String, String>): Response<Unit> {
        return client.status(query)
    }

}