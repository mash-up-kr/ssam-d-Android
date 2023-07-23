package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.chat.MessageDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageDetailResponse(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long,
    val isAlive: Boolean,
    val isMine: Boolean,
    val isReplyable: Boolean
) : DomainMapper<MessageDetail> {
    override fun toDomainModel(): MessageDetail {
        return MessageDetail(
            id = id,
            profileImage = profileImage,
            nickname = nickname,
            keywords = keywords,
            matchingKeywordCount = matchingKeywordCount,
            content = content,
            receivedTimeMillis = receivedTimeMillis,
            isAlive = isAlive,
            isMine = isMine,
            isReplyable = isReplyable
        )
    }
}