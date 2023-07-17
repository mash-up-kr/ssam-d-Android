package com.mashup.data.source.remote.datasource

import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.service.ChatService
import javax.inject.Inject

class RemoteChatDataSource @Inject constructor(
    private val chatService: ChatService
) {

    suspend fun getChatDetail(
        roomId: Long,
        chatId: Long
    ): ChatDetailResponse {
        val response = chatService.getChatDetail(roomId, chatId)
        return response.data ?: throw Exception(response.message)
    }
}