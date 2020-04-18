package com.okysoft.infra

import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor constructor(
        private val dispatcher: ApplicationDispatcher
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code == 401) {
            dispatcher.logout()
        }
        return response
    }

}