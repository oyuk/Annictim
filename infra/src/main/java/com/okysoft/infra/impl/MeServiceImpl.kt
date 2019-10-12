package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Me {

    private val client = retrofit.create(AnnictService.Me::class.java)

    override fun status(query: Map<String, String>): Deferred<Response<Unit>> {
        return client.status(query)
    }

}