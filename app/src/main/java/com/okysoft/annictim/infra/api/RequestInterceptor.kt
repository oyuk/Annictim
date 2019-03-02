package com.okysoft.annictim.infra.api

import com.okysoft.annictim.infra.api.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(
        private val authRepository: AuthRepository
        ): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val token = authRepository.getStoredAccessToken()
        if (token.isNotBlank()) {
            builder.addHeader("Authorization", "Bearer ${token}")
        }
        return chain.proceed(builder.build())
    }

}
