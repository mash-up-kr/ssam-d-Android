package com.mashup.domain.usecase

import com.mashup.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): BaseUseCase<Int, Result<Unit>>() {

    override suspend fun invoke(param: Int): Result<Unit> {
        return runCatching {
            chatRepository.getChatRooms(param)
        }
    }
}