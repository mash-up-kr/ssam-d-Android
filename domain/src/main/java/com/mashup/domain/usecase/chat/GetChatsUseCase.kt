package com.mashup.domain.usecase.chat

import androidx.paging.PagingData
import com.mashup.domain.model.Chat
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : FlowUseCase<Long, PagingData<Chat>>() {

    override fun invoke(params: Long): Flow<PagingData<Chat>> {
        return chatRepository.getChats(params)
    }
}
