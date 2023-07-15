package com.mashup.domain.usecase

import com.mashup.domain.repository.UserRepository
import javax.inject.Inject

class CheckNicknameDuplicationUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<String, Result<Unit>>() {

    override suspend fun invoke(param: String): Result<Unit> {
        return runCatching {
            userRepository.getNicknameDuplication(param)
        }
    }
}
