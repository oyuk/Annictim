package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.response.ProgramsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class ProgramServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.Program {

    private val retrofitClient = retrofit.create(AnnictService.Program::class.java)

    override fun get(query: Map<String, String>): Deferred<ProgramsResponse> {
        return retrofitClient.get(query)
    }

}