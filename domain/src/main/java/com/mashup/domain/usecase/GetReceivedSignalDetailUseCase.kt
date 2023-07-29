package com.mashup.domain.usecase

import com.mashup.domain.model.signal.Signal
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
) : FlowUseCase<Long, Signal>() {

    override fun invoke(params: Long): Flow<Signal> =
        signalRepository.getReceivedSignalDetail(signalId = params)
}