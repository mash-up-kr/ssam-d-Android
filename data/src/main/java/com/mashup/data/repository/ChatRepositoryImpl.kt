package com.mashup.data.repository

import com.mashup.data.source.remote.datasource.RemoteChatDataSource
import com.mashup.domain.model.ChatDetail
import com.mashup.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val remoteChatDataSource: RemoteChatDataSource
): ChatRepository {

    override suspend fun getChatRooms(id: Long): ChatDetail {
        return remoteChatDataSource.getChats(id).toDomainModel()
    }
}