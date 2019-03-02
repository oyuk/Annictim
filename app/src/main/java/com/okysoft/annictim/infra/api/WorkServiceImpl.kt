package com.okysoft.annictim.infra.api

import com.okysoft.annictim.infra.api.model.response.WorksResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class WorkServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Works {

    private val retrofitClient = retrofit.create(AnnictService.Work::class.java)
    private val meClient = retrofit.create(AnnictService.Work.Me::class.java)

    override fun get(query: Map<String, String>): Deferred<WorksResponse> {
        return retrofitClient.get(query)
    }

    override fun me(query: Map<String, String>): Deferred<WorksResponse> {
        return meClient.me(query)
    }

}