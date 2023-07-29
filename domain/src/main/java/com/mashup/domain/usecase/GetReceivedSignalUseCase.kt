package com.mashup.domain.usecase

import androidx.paging.PagingData
import com.mashup.domain.model.signal.Signal
import com.mashup.domain.repository.SignalRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
class GetReceivedSignalUseCase @Inject constructor(
    private val signalRepository: SignalRepository
) : FlowUseCase<Unit, PagingData<Signal>>() {
    override fun invoke(params: Unit): Flow<PagingData<Signal>>
        = signalRepository.getReceivedSignal()
}