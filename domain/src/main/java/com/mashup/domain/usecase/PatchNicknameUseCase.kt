package com.mashup.domain.usecase

import com.mashup.domain.repository.UserRepository
import javax.inject.Inject

class PatchNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<String, Result<Unit>>() {

    override suspend fun invoke(param: String): Result<Unit> {
        return runCatching {
            userRepository.patchNickname(param)
        }
    }
}