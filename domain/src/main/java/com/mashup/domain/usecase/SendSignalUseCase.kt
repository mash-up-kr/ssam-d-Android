package com.mashup.domain.usecase

import com.mashup.domain.repository.SignalRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */

class SendSignalUseCase @Inject constructor(
    private val signalRepository: SignalRepository
) : CoroutineUseCase<Pair<String, List<String>>, Result<Unit>>() {

    override suspend fun invoke(param: Pair<String, List<String>>): Result<Unit> {
        val response = runCatching {
            signalRepository.postSignal(
                content = param.first,
                keywords = param.second
            )
        }
        return response
    }
}