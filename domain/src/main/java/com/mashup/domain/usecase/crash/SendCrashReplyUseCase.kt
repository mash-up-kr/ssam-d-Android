package com.mashup.domain.usecase.crash

import com.mashup.domain.repository.CrashRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class SendCrashReplyUseCase @Inject constructor(
    private val crashRepository: CrashRepository
) : CoroutineUseCase<ReceivedCrashReplyParams, Result<Unit>>() {

    override suspend fun invoke(param: ReceivedCrashReplyParams): Result<Unit> =
        crashRepository.replyCrash(
            crashId = param.crashId,
            content = param.content
        )
}

data class ReceivedCrashReplyParams(
    val crashId: Long,
    val content: String,
)