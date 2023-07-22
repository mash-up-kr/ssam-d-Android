package com.mashup.domain.usecase.login

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : CoroutineUseCase<LoginParam, Result<Unit>>() {

    override suspend fun invoke(param: LoginParam): Result<Unit> {
        return runCatching {
            userRepository.login(param)
        }
    }
}

data class LoginParam(
    val email: String?,
    val socialId: String,
    val deviceToken: String?
)
