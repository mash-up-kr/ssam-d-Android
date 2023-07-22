package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.Signal
import kotlinx.coroutines.flow.Flow

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
interface SignalRepository {

    suspend fun sendSignal(content: String, keywords: List<String>)

    fun getReceivedSignal(): Flow<PagingData<Signal>>

    fun getReceivedSignalDetail(signalId: Long): Flow<Signal>
}