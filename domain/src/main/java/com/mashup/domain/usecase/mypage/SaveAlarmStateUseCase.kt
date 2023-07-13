package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.usecase.BaseUseCase

class SaveAlarmStateUseCase(
    private val loginRepository: LoginRepository
): BaseUseCase<Boolean, Result<Unit>>() {
    override suspend fun invoke(param: Boolean): Result<Unit> {
        return runCatching {
            loginRepository.saveAlarmState(isAgree = param)
        }
    }
}