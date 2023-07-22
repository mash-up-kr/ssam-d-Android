package com.mashup.data.repository

import com.mashup.data.source.local.datasource.LocalUserDataSource
import com.mashup.data.source.remote.source.datasource.RemoteOnboardingDataSource
import com.mashup.data.util.suspendRunCatching
import com.mashup.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val remoteOnboardingDataSource: RemoteOnboardingDataSource,
    private val localUserDataSource: LocalUserDataSource
): OnboardingRepository {

    override suspend fun saveOnboardingKeywords(keywords: List<String>): Result<Unit> {
        return suspendRunCatching {
            remoteOnboardingDataSource.saveOnboardingKeywords(keywords)
        }.onSuccess {
            localUserDataSource.setKeywords(keywords)
        }.onFailure { throw Exception(it.message) }
    }
}