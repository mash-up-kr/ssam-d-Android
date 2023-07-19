package com.mashup.presentation.feature.home.model

import com.mashup.domain.model.ReceivedSignal
import com.mashup.presentation.common.base.UiMapper
import com.mashup.presentation.common.base.UiModel
import com.mashup.presentation.common.extension.getDisplayedTime

data class SignalUiModel(
    val signalId: Int = -1,
    val receiverId: Int = -1,
    val senderId: Int = -1,
    val senderName: String = "",
    val senderImageUrl: String = "",
    val signalContent: String = "",
    val keywords: List<String> = emptyList(),
    val keywordsCount: Int = -1,
    val receivedDisplayedTime: String = "",
) {
    companion object {
        fun ReceivedSignal.toUiModel() = SignalUiModel(
            signalId = signalId,
            receiverId = receiverId,
            senderId = senderId,
            senderName = senderName,
            senderImageUrl = senderImageUrl,
            signalContent = signalContent,
            keywords = keywords.map { "#$it" },
            keywordsCount = keywordsCount,
            receivedDisplayedTime = receivedTimeMillis.getDisplayedTime()
        )
    }
}
