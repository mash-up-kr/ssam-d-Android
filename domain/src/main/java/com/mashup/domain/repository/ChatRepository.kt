package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.chat.MessageDetail
import com.mashup.domain.model.Chat
import kotlinx.coroutines.flow.Flow
import com.mashup.domain.model.ChatInfo
import com.mashup.domain.model.chat.Room

interface ChatRepository {

    fun getChatInfo(id: Long): Flow<ChatInfo>

    fun getChats(id: Long): Flow<PagingData<Chat>>

    fun getMessageDetail(
        roomId: Long,
        chatId: Long
    ): Flow<Result<MessageDetail?>>
    fun getChatRooms(): Flow<PagingData<Room>>

    suspend fun disconnectRoom(roomId: Long): Result<Unit>

    suspend fun reply(roomId: Long, content: String): Result<Unit>
}
