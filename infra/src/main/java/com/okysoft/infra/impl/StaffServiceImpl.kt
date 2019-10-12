package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class StaffServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Staff {

    private val client = retrofit.create(AnnictService.Staff::class.java)

    override fun get(query: Map<String, String>): Deferred<com.okysoft.data.StaffsResponse> {
        return client.get(query)
    }

}