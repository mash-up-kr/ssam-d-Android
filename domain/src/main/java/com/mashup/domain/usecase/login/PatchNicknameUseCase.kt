package com.mashup.domain.usecase.login

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class PatchNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) : CoroutineUseCase<String, Result<Unit>>() {

    override suspend fun invoke(param: String): Result<Unit> {
        return userRepository.patchNickname(param)
    }
}