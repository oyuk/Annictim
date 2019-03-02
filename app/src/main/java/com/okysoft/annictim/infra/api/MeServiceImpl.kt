package com.okysoft.annictim.infra.api

import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class MeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Me {

    private val client = retrofit.create(AnnictService.Me::class.java)

    override fun status(query: Map<String, String>): Deferred<Unit> {
        return client.status(query)
    }

}