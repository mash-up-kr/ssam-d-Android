package com.mashup.data.repository

import com.mashup.data.source.remote.datasource.RemoteOnboardingDataSource
import com.mashup.data.source.remote.service.OnboardingService
import com.mashup.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val remoteOnboardingDataSource: RemoteOnboardingDataSource
): OnboardingRepository {

    override suspend fun saveOnboardingKeywords(keywords: List<String>) {
        remoteOnboardingDataSource.saveOnboardingKeywords(keywords)
    }
}