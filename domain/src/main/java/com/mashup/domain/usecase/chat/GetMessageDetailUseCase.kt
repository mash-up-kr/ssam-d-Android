package com.mashup.domain.usecase.chat

import com.mashup.domain.model.chat.MessageDetail
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessageDetailUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : FlowUseCase<GetMessageDetailUseCase.MessageDetailParam, MessageDetail>() {

    override fun invoke(params: MessageDetailParam): Flow<MessageDetail> {
        return chatRepository.getMessageDetail(roomId = params.roomId, chatId = params.chatId)
    }

    data class MessageDetailParam(
        val roomId: Long,
        val chatId: Long
    )
}