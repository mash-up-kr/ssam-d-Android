package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.requestbody.ReplyRequestBody
import com.mashup.data.source.remote.dto.requestbody.SignalRequest
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignalDetail
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignalResponse
import com.mashup.data.source.remote.service.SignalService
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
        signalService.postSignal(signalRequest)
    }

    suspend fun getReceivedSignal(pageNumber: Int, pageLength: Int?): ReceivedSignalResponse {
        val response =
            signalService.getReceivedSignal(pageNumber = pageNumber, pageLength = pageLength)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getReceivedSignalDetail(signalId: Long): ReceivedSignalDetail {
        val response = signalService.getReceivedSignalDetail(signalId)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun postReceivedSignalReply(signalId: Long, content: String) {
        val replyRequest = ReplyRequestBody(
            content = content
        )
        signalService.postReceivedSignalReply(signalId = signalId, replyRequest = replyRequest)
    }
}