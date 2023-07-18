package com.mashup.domain.usecase

import androidx.paging.PagingData
import com.mashup.domain.model.ReceivedSignal
import com.mashup.domain.repository.SignalRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
class GetReceivedSignalUseCase @Inject constructor(
    private val signalRepository: SignalRepository
) : FlowUseCase<Unit, PagingData<ReceivedSignal>>() {
    override fun invoke(params: Unit): Flow<PagingData<ReceivedSignal>> = flow {
        signalRepository.getReceivedSignal()
    }
}