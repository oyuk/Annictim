package com.okysoft.annictim.API

import com.okysoft.annictim.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(
        private val authRepository: AuthRepository
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code() in 400..499) {

        }
        return response
    }

}