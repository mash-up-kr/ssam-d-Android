package com.mashup.domain.usecase

import com.mashup.domain.model.ReceivedSignal
import com.mashup.domain.repository.SignalRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/21
 */
class GetReceivedSignalDetailUseCase @Inject constructor(
    private val signalRepository: SignalRepository
) : FlowUseCase<Long, ReceivedSignal>() {

    override fun invoke(params: Long): Flow<ReceivedSignal> =
        signalRepository.getReceivedSignalDetail(signalId = params)
}