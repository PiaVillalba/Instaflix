package com.piavillalba.core.network

import okhttp3.Interceptor
import okhttp3.Response

class APIKeyInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().url.newBuilder()
            .addQueryParameter(API_KEY, "889bff8add8960b1fe25726019041e69").build()

        val newRequest = chain.request().newBuilder().url(request)
            .build()

        return chain.proceed(newRequest)
    }
}

private const val API_KEY = "api_key"
