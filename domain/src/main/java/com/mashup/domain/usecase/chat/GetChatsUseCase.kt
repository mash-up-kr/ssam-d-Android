package com.mashup.domain.usecase.chat

import com.mashup.domain.model.Chats
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): CoroutineUseCase<GetChatsParam, Flow<Chats>>() {

    override suspend fun invoke(param: GetChatsParam): Flow<Chats> {
        return chatRepository.getChats(param)
    }
}

data class GetChatsParam(
    val id: Long,
    val pageNo: Int,
    val pageLength: Int
)