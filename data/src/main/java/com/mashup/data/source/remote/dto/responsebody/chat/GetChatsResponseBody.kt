package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.model.Chat
import com.mashup.domain.model.ChatDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatsResponseBody(
    val id: Long,
    val keywords: List<String>,
    val matchingUserName: String,
    val matchingUserProfileImage: String,
    val chatColor: String,
    val chat: List<ChatResponseBody>
) {
    fun toDomainModel(): ChatDetail {
        return ChatDetail(
            id = id,
            keywords = keywords,
            matchingUserName = matchingUserName,
            matchingUserProfileImage = matchingUserProfileImage,
            chatColor = chatColor,
            chat = chat.map { it.toDomainModel() }
        )
    }
}

@JsonClass(generateAdapter = true)
data class ChatResponseBody(
    val id: Long,
    val content: String,
    val senderName: String,
    val createdAt: Long
) {
    fun toDomainModel(): Chat {
        return Chat(
            id = id,
            content = content,
            senderName = senderName,
            createdAt = createdAt
        )
    }
}
