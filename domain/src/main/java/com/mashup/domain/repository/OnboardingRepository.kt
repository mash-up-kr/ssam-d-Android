package com.mashup.domain.repository

interface OnboardingRepository {

    suspend fun saveOnboardingKeywords(
        keywords: List<String>
    ): Result<Unit>
}