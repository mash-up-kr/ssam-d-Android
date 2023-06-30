package com.mashup.domain.usecase

import com.mashup.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<LoginParam, Result<Boolean>>() {

    override suspend fun invoke(param: LoginParam): Result<Boolean> {
        return runCatching {
            loginRepository.login(param)
        }
    }
}

data class LoginParam(
    val email: String?,
    val socialId: String,
    val deviceToken: String?
)
