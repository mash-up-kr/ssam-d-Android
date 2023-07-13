package com.mashup.domain.usecase

import com.mashup.domain.repository.LoginRepository
import javax.inject.Inject

class CheckNicknameDuplicationUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<String, Result<Unit>>() {

    override suspend fun invoke(param: String): Result<Unit> {
        return runCatching {
            loginRepository.getNicknameDuplication(param)
        }
    }
}
