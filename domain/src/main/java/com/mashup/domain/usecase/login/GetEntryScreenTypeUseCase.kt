package com.mashup.domain.usecase.login

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/20
 */

class GetEntryScreenTypeUseCase @Inject constructor(
    private val userRepository: UserRepository
) : CoroutineUseCase<Unit, ScreenType>() {
    override suspend fun invoke(param: Unit): ScreenType {
        return if (userRepository.getUserAccessToken().isBlank()) {
            ScreenType.LOGIN
        } else if (userRepository.getNickname().isBlank()) {
            ScreenType.NICKNAME
        } else if (userRepository.getKeywords().isEmpty()) {
            ScreenType.KEYWORD
        } else {
            ScreenType.MAIN
        }
    }
}

enum class ScreenType {
    LOGIN, NICKNAME, KEYWORD, MAIN
}
