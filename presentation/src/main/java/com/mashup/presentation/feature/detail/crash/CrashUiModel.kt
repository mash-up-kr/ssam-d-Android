package com.mashup.presentation.feature.detail.crash

import com.mashup.domain.model.Crash
import com.mashup.presentation.common.extension.getDisplayedDateWithDay

data class CrashUiModel(
    val id: Long,
    val content: String,
    val keywords: List<String>,
    val userId: Long,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val nickname: String,
    val receivedTime: String
) {
    companion object {
        fun toUiModel(domain: Crash): CrashUiModel {
            with(domain) {
                return CrashUiModel(
                    id = id,
                    content = content,
                    keywords = keywords.split(","),
                    userId = userId,
                    matchingKeywordCount = matchingKeywordCount,
                    profileImage = profileImage,
                    nickname = nickname,
                    receivedTime = receivedTimeMillis.getDisplayedDateWithDay()
                )
            }
        }
    }
}