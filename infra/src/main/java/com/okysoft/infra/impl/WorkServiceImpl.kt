package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class WorkServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Works {

    private val retrofitClient = retrofit.create(AnnictService.Work::class.java)
    private val meClient = retrofit.create(AnnictService.Work.Me::class.java)

    override suspend fun get(query: Map<String, String>): com.okysoft.data.WorksResponse {
        return retrofitClient.get(query)
    }

    override suspend fun me(query: Map<String, String>): com.okysoft.data.WorksResponse {
        return meClient.me(query)
    }

}