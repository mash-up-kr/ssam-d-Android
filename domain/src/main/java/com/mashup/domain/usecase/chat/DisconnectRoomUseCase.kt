package com.mashup.domain.usecase.chat

import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

class DisconnectRoomUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : CoroutineUseCase<Long, Unit>() {
    override suspend fun invoke(param: Long) {
        return chatRepository.disconnectRoom(param)
    }
}