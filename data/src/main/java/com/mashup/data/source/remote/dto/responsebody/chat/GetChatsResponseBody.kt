package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.Chat
import com.mashup.domain.model.Chats
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatsResponseBody(
    val pageLength: Int,
    val totalPage: Int,
    val list: List<ChatResponseBody>
): DomainMapper<Chats> {
    override fun toDomainModel(): Chats {
        return Chats(
            pageLength = pageLength,
            totalPage = totalPage,
            list = list.map { it.toDomainModel() }
        )
    }
}

@JsonClass(generateAdapter = true)
data class ChatResponseBody(
    val id: Long,
    val content: String,
    val senderName: String,
    val receivedTimeMillis: Long
): DomainMapper<Chat> {
    override fun toDomainModel(): Chat {
        return Chat(
            id = id,
            content = content,
            senderName = senderName,
            receivedTimeMillis = receivedTimeMillis
        )
    }
}
