package com.mashup.domain.usecase.chat

import com.mashup.domain.model.ChatInfo
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatInfoUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : FlowUseCase<Long, ChatInfo>() {

    override fun invoke(param: Long): Flow<ChatInfo> {
        return chatRepository.getChatInfo(param)
    }
}