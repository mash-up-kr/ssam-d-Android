package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.chat.MessageDetailResponse
import com.mashup.data.source.remote.dto.requestbody.ReplyRequestBody
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatInfoResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.GetChatsResponseBody
import com.mashup.data.source.remote.dto.responsebody.chat.ChatRoomPagingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.DELETE

interface ChatService {

    @GET("rooms/{id}")
    suspend fun getChatInfo(
        @Path("id") id: Long
    ): BaseResponse<GetChatInfoResponseBody>

    @GET("rooms/{id}/chats")
    suspend fun getChats(
        @Path("id") id: Long,
        @Query("pageNo") pageNo: Int,
        @Query("pageLength") pageLength: Int
    ): BaseResponse<GetChatsResponseBody>

    @GET("rooms")
    suspend fun getChatRooms(
        @Query("pageNo") pageNo: Int,
        @Query("pageLength") pageLength: Int?
    ): BaseResponse<ChatRoomPagingResponse>

    @GET("rooms/{roomId}/chats/{chatId}")
    suspend fun getMessageDetail(
        @Path("roomId") roomId: Long,
        @Path("chatId") chatId: Long
    ): BaseResponse<MessageDetailResponse>

    @DELETE("rooms/{roomId}")
    suspend fun disconnectRoom(
        @Path("roomId") roomId: Long
    ): BaseResponse<Any>

    @POST("rooms/{roomId}/chats")
    suspend fun reply(
        @Path("roomId") roomId: Long,
        @Body replyRequestBody: ReplyRequestBody
    ): BaseResponse<Any>
}
