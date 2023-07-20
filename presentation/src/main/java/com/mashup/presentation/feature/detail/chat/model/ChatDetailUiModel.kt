package com.mashup.presentation.feature.detail.chat.model

import com.mashup.domain.model.Chat
import com.mashup.domain.model.ChatInfo

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/30
 */
data class ChatDetailUiModel(
    val othersProfileImage: String,
    val othersNickName: String,
    val matchedKeywords: List<String>,
    val isAlive: Boolean,
    val chat: List<ChatUiModel>
) {
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

fun ChatInfo.toUiModel(chats: List<Chat>): ChatDetailUiModel {
    return ChatDetailUiModel(
        othersProfileImage = matchingUserProfileImage,
        othersNickName = matchingUserName,
        matchedKeywords = keywords,
        isAlive = isAlive,
        chat = chats.map { it.toUiModel(chatColor, matchingUserName) }
    )
}
