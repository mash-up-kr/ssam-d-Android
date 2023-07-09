package com.mashup.domain.repository

import com.mashup.domain.model.chat.ChatDetail
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getChatRooms(id: Int)

    fun getChatDetail(
        roomId: Long,
        chatId: Long
    ): Flow<ChatDetail>
}
