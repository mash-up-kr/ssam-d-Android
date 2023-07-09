package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.GetChatsResponseBody
import com.mashup.data.source.remote.service.ChatService
import javax.inject.Inject

class RemoteChatDataSource @Inject constructor(
    private val chatService: ChatService
) {

    suspend fun getChats(id: Int): BaseResponse<GetChatsResponseBody> {
        return chatService.getChats(id)
    }

    suspend fun getChatDetail(
        roomId: Long,
        chatId: Long
    ): ChatDetailResponse {
        val response = chatService.getChatDetail(roomId, chatId)
        return response.data ?: throw Exception(response.message)
    }
}
