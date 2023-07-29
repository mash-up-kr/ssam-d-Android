package com.mashup.presentation.feature.home.model

import com.mashup.domain.model.signal.Signal
import com.mashup.presentation.common.extension.getDisplayedTime

data class SignalUiModel(
    val signalId: Long,
    val receiverId: Long,
    val senderId: Long,
    val senderName: String,
    val senderImageUrl: String,
    val signalContent: String,
    val keywords: List<String>,
    val keywordsCount: Int,
    val receivedDisplayedTime: String,
) {
    companion object {
        fun Signal.toUiModel() = SignalUiModel(
            signalId = signalId,
            receiverId = receiverId ?: -1,
            senderId = senderId ?: -1,
            senderName = senderName,
            senderImageUrl = senderImageUrl,
            signalContent = signalContent,
            keywords = keywords.map { "#$it" },
            keywordsCount = keywordsCount,
            receivedDisplayedTime = receivedTimeMillis.getDisplayedTime()
        )
    }
}
