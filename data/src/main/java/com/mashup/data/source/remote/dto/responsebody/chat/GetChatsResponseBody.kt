package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.base.paging.PagedData
import com.mashup.domain.base.paging.Paging
import com.mashup.domain.model.Chat
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatsResponseBody(
    val pageLength: Int,
    val totalPage: Int,
    val list: List<ChatResponseBody>
) : DomainMapper<PagedData<List<Chat>>> {
    override fun toDomainModel(): PagedData<List<Chat>> {
        return PagedData(
            data = list.map { it.toDomainModel() },
            paging = Paging(
                loadedSize = pageLength,
                totalPage = totalPage
            )
        )
    }
}

@JsonClass(generateAdapter = true)
data class ChatResponseBody(
    val id: Long,
    val content: String,
    val senderName: String,
    val receivedTimeMillis: Long
) : DomainMapper<Chat> {
    override fun toDomainModel(): Chat {
        return Chat(
            id = id,
            content = content,
            senderName = senderName,
            receivedTimeMillis = receivedTimeMillis
        )
    }
}
