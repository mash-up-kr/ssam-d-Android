package com.mashup.presentation.feature.home.model

import com.mashup.domain.model.ReceivedSignal
import com.mashup.presentation.common.base.UiMapper
import com.mashup.presentation.common.base.UiModel
import java.util.concurrent.TimeUnit

/**
 * 임시 data model입니다.
 */
data class SignalUiModel(
    val signalId: Int = -1,
    val receiverId: Int = -1,
    val senderId: Int = -1,
    val senderName: String = "",
    val senderImageUrl: String = "",
    val signalContent: String = "",
    val keywords: List<String> = emptyList(),
    val keywordsCount: Int = -1,
    val receivedTimeMillis: String = "",
) : UiModel, UiMapper<ReceivedSignal, SignalUiModel> {
    fun getDisplayedTime(receivedTimeMillis: Long): String {
        val currentTimeMillis = System.currentTimeMillis()
        val diffMillis = currentTimeMillis - receivedTimeMillis

        return when {
            diffMillis < TimeUnit.MINUTES.toMillis(1) -> "지금"
            diffMillis < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis)
                "${minutes}분 전"
            }
            diffMillis < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(diffMillis)
                "${hours}시간 전"
            }
            diffMillis < TimeUnit.DAYS.toMillis(30) -> {
                val days = TimeUnit.MILLISECONDS.toDays(diffMillis)
                "${days}일 전"
            }
            diffMillis < TimeUnit.DAYS.toMillis(365) -> {
                val months = (TimeUnit.MILLISECONDS.toDays(diffMillis) / 30).toInt()
                "${months}달 전"
            }
            else -> {
                "오래 전"
            }
        }
    }
    override fun toUiModel(domain: ReceivedSignal) = SignalUiModel(
        signalId = domain.signalId,
        receiverId = domain.receiverId,
        senderId = domain.senderId,
        senderName = domain.senderName,
        senderImageUrl = domain.senderImageUrl,
        signalContent = domain.signalContent,
        keywords = domain.keywords.map { "#$it" },
        keywordsCount = domain.keywordsCount,
        receivedTimeMillis = getDisplayedTime(domain.receivedTimeMillis)
    )
}
