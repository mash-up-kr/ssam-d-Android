package com.mashup.domain.usecase.chat

import com.mashup.domain.model.Chats
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.BaseUseCase
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): BaseUseCase<GetChatsParam, Result<Chats>>() {

    override suspend fun invoke(param: GetChatsParam): Result<Chats> {
        return runCatching {
            chatRepository.getChats(param)
        }
    }
}

data class GetChatsParam(
    val id: Long,
    val pageNo: Int
)
