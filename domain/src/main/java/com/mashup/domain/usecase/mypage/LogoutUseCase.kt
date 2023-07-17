package com.mashup.domain.usecase.mypage

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.BaseUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
): BaseUseCase<Unit, Unit>() {
    override suspend fun invoke(param: Unit) {
        userRepository.logout()
    }
}