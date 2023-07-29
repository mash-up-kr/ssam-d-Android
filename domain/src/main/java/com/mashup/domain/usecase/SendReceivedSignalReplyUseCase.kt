package com.mashup.domain.usecase

import com.mashup.domain.repository.SignalRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
class SendReceivedSignalReplyUseCase @Inject constructor(
    private val signalRepository: SignalRepository
) : CoroutineUseCase<ReceivedSignalReplyParams, Result<Unit>>() {

    override suspend fun invoke(param: ReceivedSignalReplyParams): Result<Unit> =
        signalRepository.sendReceivedSignalReply(
            signalId = param.signalId,
            content = param.content
        )

}

data class ReceivedSignalReplyParams(
    val signalId: Long,
    val content: String,
)