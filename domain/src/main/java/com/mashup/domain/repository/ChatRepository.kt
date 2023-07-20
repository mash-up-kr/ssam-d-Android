package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.chat.MessageDetail
import com.mashup.domain.model.Chat
import kotlinx.coroutines.flow.Flow
import com.mashup.domain.model.ChatInfo
import com.mashup.domain.model.chat.Room
import com.mashup.domain.usecase.chat.GetChatsParam

interface ChatRepository {

    suspend fun getChatInfo(id: Long): Flow<ChatInfo>

    suspend fun getChats(param: GetChatsParam): Flow<PagingData<Chat>>

    fun getMessageDetail(
        roomId: Long,
        chatId: Long
    ): Flow<MessageDetail>
    fun getChatRooms(): Flow<PagingData<Room>>

    suspend fun disconnectRoom(roomId: Long): Result<Unit>

    suspend fun reply(roomId: Long, content: String): Result<Unit>
}
