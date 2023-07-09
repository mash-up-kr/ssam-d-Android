package com.mashup.domain.usecase

import com.mashup.domain.model.ChatDetail
import com.mashup.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): BaseUseCase<Int, Result<ChatDetail>>() {

    override suspend fun invoke(param: Int): Result<ChatDetail> {
        return runCatching {
            chatRepository.getChatRooms(param)
        }
    }
}