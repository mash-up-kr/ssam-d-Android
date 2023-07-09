package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.dto.responsebody.GetChatRoomsResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {

    @GET("rooms")
    suspend fun getChatRooms(): BaseResponse<GetChatRoomsResponseBody>

    @GET("rooms/{roomId}/chats/{chatId}")
    suspend fun getChatDetail(
        @Path("roomId") roomId: Long,
        @Path("chatId") chatId: Long
    ): BaseResponse<ChatDetailResponse>
}