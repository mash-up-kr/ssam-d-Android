package com.mashup.data.network

import android.util.Log
import com.mashup.data.source.local.datasource.LocalUserDataSource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val localUserDataSource: LocalUserDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()

        val jwt = localUserDataSource.getToken()
        if (jwt.isNotEmpty()) {
            Log.d("TokenInterceptor", jwt)
            requestBuilder.header("Authorization", "Bearer $jwt")
        } else {
            Log.d("TokenInterceptor", "Token is empty")
        }

        return chain.proceed(requestBuilder.build())
    }
}