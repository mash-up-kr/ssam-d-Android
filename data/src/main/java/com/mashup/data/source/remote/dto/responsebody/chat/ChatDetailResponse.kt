package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.chat.ChatDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatDetailResponse(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long
) : DomainMapper<ChatDetail> {
    override fun toDomainModel(): ChatDetail {
        return ChatDetail(
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