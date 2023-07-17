package com.mashup.domain.usecase.chat

import com.mashup.domain.model.chat.ChatDetail
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatDetailUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : BaseUseCase<GetChatDetailUseCase.ChatDetailParam, Flow<ChatDetail>>() {

    override suspend fun invoke(param: ChatDetailParam): Flow<ChatDetail> {
        return chatRepository.getChatDetail(roomId = param.roomId, chatId = param.chatId)
    }

    data class ChatDetailParam(
        val roomId: Long,
        val chatId: Long
    )
}