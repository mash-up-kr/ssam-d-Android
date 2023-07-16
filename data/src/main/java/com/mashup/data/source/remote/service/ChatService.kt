package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatsResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {

    @GET("rooms/{id}/chats")
    suspend fun getChats(
        @Path("id") id: Long
    ): BaseResponse<GetChatsResponseBody>

    @GET("rooms/{roomId}/chats/{chatId}")
    suspend fun getChatDetail(
        @Path("roomId") roomId: Long,
        @Path("chatId") chatId: Long
    ): BaseResponse<ChatDetailResponse>
}