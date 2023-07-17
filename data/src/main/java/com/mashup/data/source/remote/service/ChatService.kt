package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.chat.ChatDetailResponse
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatInfoResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatsResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {

    @GET("rooms/{id}")
    suspend fun getChatInfo(
        @Path("id") id: Long
    ): BaseResponse<GetChatInfoResponseBody>

    @GET("rooms/{id}/chats")
    suspend fun getChats(
        @Path("id") id: Long,
        @Query("pageNo") pageNo: Int,
        @Query("pageLength") pageLength: Int = PAGE_LENGTH
    ): BaseResponse<GetChatsResponseBody>

    @GET("rooms/{roomId}/chats/{chatId}")
    suspend fun getChatDetail(
        @Path("roomId") roomId: Long,
        @Path("chatId") chatId: Long
    ): BaseResponse<ChatDetailResponse>

    companion object {
        const val PAGE_LENGTH = 10
    }
}
