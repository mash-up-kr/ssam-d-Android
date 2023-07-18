package com.mashup.domain.usecase.chat

import com.mashup.domain.model.ChatInfo
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatInfoUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : CoroutineUseCase<Long, Flow<ChatInfo>>() {

    override suspend fun invoke(param: Long): Flow<ChatInfo> {
        return chatRepository.getChatInfo(param)
    }
}