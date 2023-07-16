package com.mashup.domain.usecase.chat

import com.mashup.domain.model.ChatInfo
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetChatInfoUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): BaseUseCase<Long, Result<ChatInfo>>() {

    override suspend fun invoke(param: Long): Result<ChatInfo> {
        return runCatching {
            chatRepository.getChatInfo(param)
        }
    }
}