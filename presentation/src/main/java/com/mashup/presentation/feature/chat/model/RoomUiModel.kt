package com.mashup.presentation.feature.chat.model

import com.mashup.domain.model.chat.Room
import com.mashup.presentation.common.extension.getDisplayedTime

data class RoomUiModel (
    val id: Long,
    val keywords: List<String>,
    val recentSignalContent: String,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val receivedTime: String,
    val nickname: String,
    val isChatRead: Boolean
) {
    companion object {
        fun fromDomainModel(domain: Room): RoomUiModel {
            return with(domain) {
                RoomUiModel(
                    id = id,
                    keywords = keywords,
                    recentSignalContent = recentSignalContent,
                    matchingKeywordCount = matchingKeywordCount,
                    profileImage = profileImage,
                    receivedTime = recentSignalReceivedTimeMillis.getDisplayedTime(),
                    nickname = nickname,
                    isChatRead = isChatRead
                )
            }
        }
    }
}
