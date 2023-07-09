package com.mashup.data.repository

import com.mashup.data.source.remote.source.datasource.RemoteChatDataSource
import com.mashup.domain.model.chat.ChatDetail
import com.mashup.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val remoteChatDataSource: RemoteChatDataSource
) : ChatRepository {

    override suspend fun getChatRooms(id: Int) {
        return remoteChatDataSource.getChats(id).toDomainModel()
    }

    override fun getChatDetail(roomId: Long, chatId: Long): Flow<ChatDetail> = flow{
        val result = runCatching {
            remoteChatDataSource.getChatDetail(roomId, chatId).toDomainModel()
        }.getOrThrow()
        emit(result)
    }
}
