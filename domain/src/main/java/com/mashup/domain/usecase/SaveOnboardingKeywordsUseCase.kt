package com.mashup.domain.usecase

import com.mashup.domain.repository.OnboardingRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class SaveOnboardingKeywordsUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : CoroutineUseCase<List<String>, Result<Unit>>() {

    override suspend fun invoke(param: List<String>): Result<Unit> {
        return runCatching {
            onboardingRepository.saveOnboardingKeywords(param)
        }
    }
}