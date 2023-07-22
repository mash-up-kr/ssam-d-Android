package com.mashup.presentation.feature.detail.message.model

import com.mashup.domain.model.chat.MessageDetail
import java.text.SimpleDateFormat
import java.util.*

data class MessageDetailUiModel(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long,
    val isAlive: Boolean,
    val isMine: Boolean
) {
    companion object {
        fun fromDomainModel(domain: MessageDetail): MessageDetailUiModel {
            with(domain) {
                return MessageDetailUiModel(
                    id = id,
                    profileImage = profileImage,
                    nickname = nickname,
                    keywords = keywords.map { "#$it" },
                    matchingKeywordCount = matchingKeywordCount,
                    content = content,
                    receivedTimeMillis = receivedTimeMillis,
                    isAlive = isAlive,
                    isMine = isMine
                )
            }
        }
    }
}