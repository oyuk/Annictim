package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class ProgramServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Program {

    private val retrofitClient = retrofit.create(AnnictService.Program::class.java)

    override fun get(query: Map<String, String>): Deferred<com.okysoft.data.ProgramsResponse> {
        return retrofitClient.get(query)
    }

}