package com.mashup.data.source.remote.dto.responsebody.signal

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.ReceivedSignal
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignal as DReceivedSignal

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/16
 */
@JsonClass(generateAdapter = true)
data class ReceivedSignalResponse(
    @field:Json(name = "pageLength")
    val pageLength: Int,
    @field:Json(name = "totalPage")
    val totalPage: Int,
    @field:Json(name = "list")
    val receivedSignals: List<DReceivedSignal>,
)

@JsonClass(generateAdapter = true)
data class ReceivedSignal(
    @field:Json(name = "signalId")
    val signalId: Int,
    @field:Json(name = "receiverId")
    val receiverId: Int,
    @field:Json(name = "senderId")
    val senderId: Int,
    @field:Json(name = "senderName")
    val senderName: String,
    @field:Json(name = "senderProfileImageUrl")
    val senderImageUrl: String,
    @field:Json(name = "content")
    val signalContent: String,
    @field:Json(name = "keywords")
    val keywords: List<String>,
    @field:Json(name = "keywordsCount")
    val keywordsCount: Int,
    @field:Json(name = "receivedTimeMillis")
    val receivedTimeMillis: Long,
) : DomainMapper<ReceivedSignal> {
    override fun toDomainModel(): ReceivedSignal =
        ReceivedSignal(
            signalId = signalId,
            receiverId = receiverId,
            senderId = senderId,
            senderName = senderName,
            senderImageUrl = senderImageUrl,
            signalContent = signalContent,
            keywords = keywords,
            keywordsCount = keywordsCount,
            receivedTimeMillis = receivedTimeMillis,
        )
}
