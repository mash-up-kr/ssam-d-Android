package com.mashup.presentation.feature.signal.received.model

import com.mashup.domain.model.signal.Signal
import com.mashup.presentation.common.extension.getDisplayedDateWithDay

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
data class ReceivedSignalDetailUiModel(
    val signalId: Long,
    val profileImageUrl: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: String,
) {
    companion object {
        fun Signal.toUiModel() = ReceivedSignalDetailUiModel(
            signalId = signalId,
            profileImageUrl = senderImageUrl,
            nickname = senderName,
            keywords = keywords.map { "#$it" },
            matchingKeywordCount = keywordsCount,
            content = signalContent,
            receivedTimeMillis = receivedTimeMillis.getDisplayedDateWithDay()
        )
    }
}
