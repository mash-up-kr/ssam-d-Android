package com.mashup.domain.usecase.chat

import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class ReplyUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : CoroutineUseCase<ReplyUseCase.Param, Result<Unit>>() {
    override suspend fun invoke(param: Param): Result<Unit> {
        return chatRepository.reply(roomId = param.roomId, content = param.content)
    }

    data class Param(
        val roomId: Long,
        val content: String
    )
}