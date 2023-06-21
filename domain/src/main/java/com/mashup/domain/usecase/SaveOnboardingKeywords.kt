package com.mashup.domain.usecase

import com.mashup.domain.repository.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingKeywords @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : BaseUseCase<List<String>, Result<Unit>>() {

    override suspend fun invoke(param: List<String>): Result<Unit> {
        return runCatching {
            onboardingRepository.saveOnboardingKeywords(param)
        }
    }
}