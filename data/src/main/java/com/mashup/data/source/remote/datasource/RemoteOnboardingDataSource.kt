package com.mashup.data.source.remote.datasource

import com.mashup.data.source.remote.dto.requestbody.KeywordsRequestBody
import com.mashup.data.source.remote.service.OnboardingService
import javax.inject.Inject

class RemoteOnboardingDataSource @Inject constructor(
    val onboardingService: OnboardingService
) {
    suspend fun saveOnboardingKeywords(keywords: List<String>) {
        onboardingService.saveOnboardingKeywords(KeywordsRequestBody(keywords))
    }
}