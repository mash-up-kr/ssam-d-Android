package com.mashup.presentation.feature.detail.chat.model

import com.mashup.domain.model.ChatInfo

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/30
 */
data class ChatInfoUiModel(
    val othersProfileImage: String,
    val othersNickName: String,
    val matchedKeywords: List<String>,
    val isAlive: Boolean
) {
    fun fromSignalZone() = matchedKeywords.isEmpty()

    companion object {
        fun ChatInfo.toUiModel(): ChatInfoUiModel {
            return ChatInfoUiModel(
                othersProfileImage = matchingUserProfileImage,
                othersNickName = matchingUserName,
                matchedKeywords = keywords.map { "#$it" },
                isAlive = isAlive
            )
        }
    }

    fun getMatchedKeywordSummery(): List<String> {
        val maxKeywordCount = 3

        return mutableListOf<String>().apply {
            if (matchedKeywords.size > maxKeywordCount) {
                addAll(matchedKeywords.subList(0, maxKeywordCount))
                add(
                    "+${matchedKeywords.size.minus(maxKeywordCount)}"
                )
            } else {
                addAll(matchedKeywords)
            }
        }
    }
}
