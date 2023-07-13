package com.mashup.domain.usecase.mypage

import com.mashup.domain.model.User
import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.usecase.BaseUseCase

class GetUserInformationUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase<Long, Result<User>>() {
    override suspend fun invoke(param: Long): Result<User> {
        return runCatching {
            loginRepository.getUser(param)
        }
    }
}