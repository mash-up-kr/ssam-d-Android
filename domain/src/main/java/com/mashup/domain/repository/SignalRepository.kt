package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.ReceivedSignal
import kotlinx.coroutines.flow.Flow

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
interface SignalRepository {

    suspend fun postSignal(content: String, keywords: List<String>)

    fun getReceivedSignal(): Flow<PagingData<ReceivedSignal>>

    fun getReceivedSignalDetail(signalId: Long): Flow<ReceivedSignal>
}