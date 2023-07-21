package com.mashup.data.network

import com.mashup.data.source.local.datasource.LocalUserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val localUserDataSource: LocalUserDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder().apply {
            val token = runBlocking(Dispatchers.IO) {
                runCatching { localUserDataSource.getToken() }.getOrDefault("")
            }
            addHeader(AUTHORIZATION_KEY, "Bearer $token")
        }.build()
        return chain.proceed(requestBuilder)
    }

    companion object {
        private const val AUTHORIZATION_KEY = "Authorization"
    }
}