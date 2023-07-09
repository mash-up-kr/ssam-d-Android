package com.mashup.presentation.feature.detail.chat.model

import com.mashup.domain.model.ChatDetail

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/30
 */
data class ChatDetailUiModel(
    val othersProfileImage: String,
    val othersNickName: String,
    val matchedKeywords: List<String>,
    val chat: List<MessageUiModel>
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

fun ChatDetail.toUiModel(): ChatDetailUiModel {
    return ChatDetailUiModel(
        othersProfileImage = matchingUserProfileImage,
        othersNickName = matchingUserName,
        matchedKeywords = keywords,
        chat = chat.map { it.toUiModel(chatColor, matchingUserName) }
    )
}