package com.okysoft.annictim.api

import io.reactivex.Completable
import retrofit2.Retrofit
import javax.inject.Inject

class MeServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Me {

    private val client = retrofit.create(AnnictService.Me::class.java)

    override fun status(query: Map<String, String>): Completable {
        return client.status(query)
    }

}