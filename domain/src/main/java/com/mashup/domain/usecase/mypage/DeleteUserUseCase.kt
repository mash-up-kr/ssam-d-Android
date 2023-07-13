package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.usecase.BaseUseCase

class DeleteUserUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase<Long, Result<Unit>>() {
    override suspend fun invoke(param: Long): Result<Unit> {
        return runCatching {
            loginRepository.deleteUser(id = param)
        }
    }
}