package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.responsebody.chat.MessageDetailResponse
import com.mashup.data.source.remote.dto.requestbody.ReplyRequestBody
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatInfoResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatsResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.ChatRoomPagingResponse
import com.mashup.data.source.remote.service.ChatService
import javax.inject.Inject

class RemoteChatDataSource @Inject constructor(
    private val chatService: ChatService
) {

    suspend fun getChatInfo(id: Long): GetChatInfoResponseBody {
        val response = chatService.getChatInfo(id)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getChats(id: Long, pageNo: Int, pageLength: Int): GetChatsResponseBody {
        val response = chatService.getChats(id, pageNo, pageLength)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getMessageDetail(
        roomId: Long,
        chatId: Long
    ): MessageDetailResponse {
        val response = chatService.getMessageDetail(roomId, chatId)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getChatRooms(
        pageNo: Int,
        loadSize: Int?
    ): ChatRoomPagingResponse {
        val response = chatService.getChatRooms(pageNo, loadSize)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun disconnectRoom(roomId: Long) {
        chatService.disconnectRoom(roomId)
    }

    suspend fun reply(
        roomId: Long,
        content: String
    ) {
        val requestBody = ReplyRequestBody(content = content)
        chatService.reply(roomId, requestBody)
    }
}
