package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class WithdrawalUseCase @Inject constructor(
    private val userRepository: UserRepository
): CoroutineUseCase<Long, Result<Unit>>() {
    override suspend fun invoke(param: Long): Result<Unit> {
        return userRepository.deleteUser(userId = param)
    }
}