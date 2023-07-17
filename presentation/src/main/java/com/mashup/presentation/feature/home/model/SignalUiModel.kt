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
) : UiModel, UiMapper<ReceivedSignal, SignalUiModel> {
    override fun toUiModel(domain: ReceivedSignal) = SignalUiModel(
        signalId = domain.signalId,
        receiverId = domain.receiverId,
        senderId = domain.senderId,
        senderName = domain.senderName,
        senderImageUrl = domain.senderImageUrl,
        signalContent = domain.signalContent,
        keywords = domain.keywords.map { "#$it" },
        keywordsCount = domain.keywordsCount,
        receivedDisplayedTime = domain.receivedTimeMillis.getDisplayedTime()
    )
}
