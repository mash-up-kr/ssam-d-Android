package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase

class DeleteUserUseCase(
    private val userRepository: UserRepository
): CoroutineUseCase<Long, Result<Unit>>() {
    override suspend fun invoke(param: Long): Result<Unit> {
        return runCatching {
            userRepository.deleteUser(id = param)
        }
    }
}