package com.mashup.data.source.remote.datasource

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.GetChatRoomsResponseBody
import com.mashup.data.source.remote.service.ChatService
import javax.inject.Inject

class RemoteChatDataSource @Inject constructor(
    private val chatService: ChatService
){

    suspend fun getChatRooms(): BaseResponse<GetChatRoomsResponseBody> {
        return chatService.getChatRooms()
    }
}