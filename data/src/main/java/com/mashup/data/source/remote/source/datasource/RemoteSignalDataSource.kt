package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.requestbody.SignalRequest
import com.mashup.data.source.remote.service.SignalService
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignal
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignalResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
@Singleton
class RemoteSignalDataSource @Inject constructor(
    private val signalService: SignalService
) {
    suspend fun postSignal(content: String, keywords: List<String>) {
        val signalRequest = SignalRequest(
            content = content,
            keywords = keywords
        )
        signalService.sendSignal(signalRequest)
    }

    suspend fun getReceivedSignal(pageNumber: Int): ReceivedSignalResponse {
        val response = signalService.getReceivedSignal(pageNumber = pageNumber)
        return response.data ?: throw Exception(response.message)
    }
}