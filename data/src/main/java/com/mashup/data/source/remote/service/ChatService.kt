package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.GetChatRoomsResponseBody
import retrofit2.http.GET

interface ChatService {

    @GET("rooms")
    suspend fun getChatRooms(): BaseResponse<GetChatRoomsResponseBody>
}