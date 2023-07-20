package com.mashup.presentation.feature.detail.message.model

import com.mashup.domain.model.chat.MessageDetail

data class MessageDetailUiModel(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long
) {
    companion object {
        fun fromDomainModel(domain: MessageDetail): MessageDetailUiModel {
            with(domain) {
                return MessageDetailUiModel(
                    id = id,
                    profileImage = profileImage,
                    nickname = nickname,
                    keywords = keywords,
                    matchingKeywordCount = matchingKeywordCount,
                    content = content,
                    receivedTimeMillis = receivedTimeMillis
                )
            }
        }
    }
}