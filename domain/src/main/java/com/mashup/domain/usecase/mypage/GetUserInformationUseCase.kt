package com.mashup.domain.usecase.mypage

import com.mashup.domain.model.User
import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase

class GetUserInformationUseCase(
    private val userRepository: UserRepository
): CoroutineUseCase<Long, Result<User>>() {
    override suspend fun invoke(param: Long): Result<User> {
        return runCatching {
            userRepository.getUser(param)
        }
    }
}