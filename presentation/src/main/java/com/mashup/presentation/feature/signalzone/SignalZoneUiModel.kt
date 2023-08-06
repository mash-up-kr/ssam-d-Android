package com.mashup.presentation.feature.signalzone

import com.mashup.domain.model.Crash
import com.mashup.presentation.common.extension.getDisplayedTime

data class SignalZoneUiModel(
    val crashId: Long,
    val nickname: String,
    val profileImage: String,
    val receivedDisplayedTime: String,
    val content: String
) {
    companion object {
        fun toUiModel(domain: Crash): SignalZoneUiModel {
            return SignalZoneUiModel(
                crashId = domain.id,
                nickname = domain.nickname,
                profileImage = domain.profileImage,
                receivedDisplayedTime = domain.receivedTimeMillis.getDisplayedTime(),
                content = domain.content
            )
        }
    }
}