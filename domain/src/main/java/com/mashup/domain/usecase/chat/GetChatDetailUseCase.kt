package com.mashup.domain.usecase.chat

import com.mashup.domain.model.chat.ChatDetail
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatDetailUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : FlowUseCase<GetChatDetailUseCase.ChatDetailParam, ChatDetail>() {

    override fun invoke(params: ChatDetailParam): Flow<ChatDetail> {
        return chatRepository.getChatDetail(roomId = params.roomId, chatId = params.chatId)
    }

    data class ChatDetailParam(
        val roomId: Long,
        val chatId: Long
    )
}