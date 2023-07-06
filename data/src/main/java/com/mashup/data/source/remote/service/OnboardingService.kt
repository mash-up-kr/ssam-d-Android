package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.KeywordsRequestBody
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface OnboardingService {

    @POST("keywords")
    suspend fun saveOnboardingKeywords(
        @Body keywords: KeywordsRequestBody
    ): BaseResponse<Any>
}