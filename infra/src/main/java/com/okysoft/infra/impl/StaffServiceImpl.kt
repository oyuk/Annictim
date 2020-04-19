package com.okysoft.infra.impl

import com.okysoft.infra.response.StaffsResponse
import com.okysoft.infra.AnnictService
import retrofit2.Retrofit
import javax.inject.Inject

class StaffServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Staff {

    private val client = retrofit.create(AnnictService.Staff::class.java)

    override suspend fun get(query: Map<String, String>): StaffsResponse {
        return client.get(query)
    }

}