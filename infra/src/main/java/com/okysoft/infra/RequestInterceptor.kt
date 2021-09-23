package com.okysoft.infra

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(
        private val authRepository: com.okysoft.infra.repository.AuthRepository
        ): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val token = authRepository.getStoredAccessToken()
        if (!token.isNullOrEmpty()) {
            builder.addHeader("Authorization", "Bearer ${token}")
        }
        return chain.proceed(builder.build())
    }

}
