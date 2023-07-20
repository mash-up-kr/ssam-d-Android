package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.chat.ChatDetail
import kotlinx.coroutines.flow.Flow
import com.mashup.domain.model.ChatInfo
import com.mashup.domain.model.Chats
import com.mashup.domain.model.chat.Room
import com.mashup.domain.usecase.chat.GetChatsParam

interface ChatRepository {

    suspend fun getChatInfo(id: Long): Flow<ChatInfo>

    suspend fun getChats(param: GetChatsParam): Flow<Chats>

    fun getChatDetail(
        roomId: Long,
        chatId: Long
    ): Flow<ChatDetail>
    fun getChatRooms(): Flow<PagingData<Room>>

    suspend fun disconnectRoom(roomId: Long): Result<Unit>
}
