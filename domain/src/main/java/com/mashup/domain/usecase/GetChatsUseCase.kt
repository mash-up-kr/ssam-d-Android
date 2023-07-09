package com.mashup.domain.usecase

import com.mashup.domain.model.ChatDetail
import com.mashup.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): BaseUseCase<Long, Result<ChatDetail>>() {

    override suspend fun invoke(param: Long): Result<ChatDetail> {
        return runCatching {
            chatRepository.getChatRooms(param)
        }
    }
}