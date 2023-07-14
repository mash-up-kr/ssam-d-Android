package com.mashup.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val appHeaderProvider: AppHeaderProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()

        val jwt = appHeaderProvider.getToken()
        if (jwt.isNotEmpty()) {
            Log.d("TokenInterceptor", jwt)
            requestBuilder.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjg4NTY0NDQ2LCJleHAiOjE2OTcyMDQ0NDZ9.-qw4gfZ-fmQjyK6U7FT492UjUSQGCZkfTMn1k3dj8wA")
        } else {
            Log.d("TokenInterceptor", "Token is empty")
        }

        return chain.proceed(requestBuilder.build())
    }
}