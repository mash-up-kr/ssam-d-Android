package com.mashup.domain.usecase.mypage

import com.mashup.domain.model.User
import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val userRepository: UserRepository
): CoroutineUseCase<Unit, Result<User>>() {
    override suspend fun invoke(param: Unit): Result<User> {
        return runCatching {
            userRepository.getUser()
        }
    }
}