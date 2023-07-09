package com.mashup.domain.usecase

import com.mashup.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatRoomsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): BaseUseCase<Unit, Result<Unit>>() {

    override suspend fun invoke(param: Unit): Result<Unit> {
        return runCatching {
            chatRepository.getChatRooms()
        }
    }
}