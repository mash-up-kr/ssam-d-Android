package com.mashup.data.repository

import com.mashup.data.source.remote.source.datasource.RemoteChatDataSource
import com.mashup.domain.model.chat.ChatDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.mashup.domain.model.ChatInfo
import com.mashup.domain.model.Chats
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.chat.GetChatsParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val remoteChatDataSource: RemoteChatDataSource
) : ChatRepository {

    override suspend fun getChatInfo(id: Long): Flow<ChatInfo>  = flow {
        val result = runCatching {
            remoteChatDataSource.getChatInfo(id).toDomainModel()
        }.getOrThrow()
        emit(result)
    }

    override suspend fun getChats(param: GetChatsParam): Flow<Chats> = flow {
        val result = runCatching {
            with(param) {
                remoteChatDataSource.getChats(id, pageNo).toDomainModel()
            }
        }.getOrThrow()
        emit(result)
    }

    override fun getChatDetail(roomId: Long, chatId: Long): Flow<ChatDetail> = flow{
        val result = runCatching {
            remoteChatDataSource.getChatDetail(roomId, chatId).toDomainModel()
        }.getOrThrow()
        emit(result)
    }
}
