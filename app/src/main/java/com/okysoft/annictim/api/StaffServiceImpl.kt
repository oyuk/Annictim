package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.StaffResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class StaffServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Staff {

    private val client = retrofit.create(AnnictService.Staff::class.java)

    override fun get(params: Map<String, String>): Single<StaffResponse> {
        return client.get(params)
    }

}