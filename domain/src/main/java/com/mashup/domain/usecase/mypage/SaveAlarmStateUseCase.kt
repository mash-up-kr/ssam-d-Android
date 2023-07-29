package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class SaveAlarmStateUseCase @Inject constructor(
    private val userRepository: UserRepository
): CoroutineUseCase<Boolean, Result<Unit>>() {
    override suspend fun invoke(param: Boolean): Result<Unit> {
        return runCatching {
            userRepository.patchAlarm(isAgree = param)
        }
    }
}