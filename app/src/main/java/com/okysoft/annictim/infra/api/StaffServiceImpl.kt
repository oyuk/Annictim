package com.okysoft.annictim.infra.api

import com.okysoft.annictim.infra.api.model.response.StaffsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class StaffServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Staff {

    private val client = retrofit.create(AnnictService.Staff::class.java)

    override fun get(params: Map<String, String>): Deferred<StaffsResponse> {
        return client.get(params)
    }

}