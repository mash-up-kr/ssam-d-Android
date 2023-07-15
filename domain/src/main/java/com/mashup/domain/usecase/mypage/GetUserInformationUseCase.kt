package com.mashup.domain.usecase.mypage

import com.mashup.domain.model.User
import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.BaseUseCase

class GetUserInformationUseCase(
    private val userRepository: UserRepository
): BaseUseCase<Long, Result<User>>() {
    override suspend fun invoke(param: Long): Result<User> {
        return runCatching {
            userRepository.getUser(param)
        }
    }
}