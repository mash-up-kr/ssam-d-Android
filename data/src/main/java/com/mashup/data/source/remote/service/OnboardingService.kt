package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import retrofit2.http.PATCH
import retrofit2.http.Query

interface OnboardingService {

    @PATCH("users/onboarding")
    suspend fun saveOnboardingKeywords(
        @Query("keywords") keywords: List<String>
    ): BaseResponse<Unit>
}