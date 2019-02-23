package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.CastResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class CastServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Cast {

    private val client = retrofit.create(AnnictService.Cast::class.java)

    override fun get(params: Map<String, String>): Deferred<CastResponse> {
        return client.get(params)
    }

}