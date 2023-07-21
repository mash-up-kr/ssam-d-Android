package com.mashup.data.repository

import androidx.paging.PagingData
import com.mashup.data.source.remote.source.datasource.RemoteChatDataSource
import com.mashup.data.util.createPager
import com.mashup.data.util.suspendRunCatching
import com.mashup.domain.model.chat.MessageDetail
import com.mashup.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import com.mashup.domain.model.chat.Room
import kotlinx.coroutines.flow.flow
import com.mashup.domain.model.ChatInfo
import com.mashup.domain.model.Chats
import com.mashup.domain.usecase.chat.GetChatsParam
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val remoteChatDataSource: RemoteChatDataSource
) : ChatRepository {

    override suspend fun getChatInfo(id: Long): Flow<ChatInfo> = flow {
        val result = runCatching {
            remoteChatDataSource.getChatInfo(id).toDomainModel()
        }.getOrThrow()
        emit(result)
    }

    override suspend fun getChats(param: GetChatsParam): Flow<Chats> = flow {
        val result = runCatching {
            with(param) {
                remoteChatDataSource.getChats(id, pageNo, pageLength).toDomainModel()
            }
        }.getOrThrow()
        emit(result)
    }

    override fun getMessageDetail(roomId: Long, chatId: Long): Flow<MessageDetail> = flow {
        val result = suspendRunCatching {
            remoteChatDataSource.getMessageDetail(roomId, chatId).toDomainModel()
        }.getOrThrow()
        emit(result)
    }

    override fun getChatRooms(): Flow<PagingData<Room>> {
        return createPager { page, loadSize ->
            remoteChatDataSource.getChatRooms(page, loadSize).toDomainModel()
        }.flow
    }

    override suspend fun disconnectRoom(roomId: Long): Result<Unit> {
        return suspendRunCatching {
            remoteChatDataSource.disconnectRoom(roomId)
        }
    }

    override suspend fun reply(roomId: Long, content: String): Result<Unit> {
        return suspendRunCatching {
            remoteChatDataSource.reply(roomId, content)
        }
    }
}