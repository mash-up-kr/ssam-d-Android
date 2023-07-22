package com.mashup.domain.usecase.login

import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/20
 */
class GetAccessTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) : CoroutineUseCase<Unit, String>() {
    override suspend fun invoke(param: Unit): String {
        return userRepository.getUserAccessToken()
    }
}