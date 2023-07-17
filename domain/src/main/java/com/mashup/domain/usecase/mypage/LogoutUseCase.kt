package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
): CoroutineUseCase<Unit, Unit>() {
    override suspend fun invoke(param: Unit) {
        userRepository.logout()
    }
}