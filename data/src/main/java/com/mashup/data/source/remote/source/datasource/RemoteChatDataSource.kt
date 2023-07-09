package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.GetChatRoomsResponseBody
import com.mashup.data.source.remote.service.ChatService
import javax.inject.Inject

class RemoteChatDataSource @Inject constructor(
    private val chatService: ChatService
) {
    suspend fun getChatRooms(): BaseResponse<GetChatRoomsResponseBody> {
        return chatService.getChatRooms()
    }

    suspend fun getChatDetail(
        roomId: Long,
        chatId: Long
    ): ChatDetailResponse {
        val response = chatService.getChatDetail(roomId, chatId)
        return response.data ?: throw Exception(response.message)
    }
}
