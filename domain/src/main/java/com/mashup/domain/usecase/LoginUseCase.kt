package com.mashup.domain.usecase

import com.mashup.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<LoginParam, Result<Unit>>() {

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
