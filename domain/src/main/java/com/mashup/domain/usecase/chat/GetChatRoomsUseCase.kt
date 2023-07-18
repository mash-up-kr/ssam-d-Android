package com.mashup.domain.usecase.chat

import androidx.paging.PagingData
import com.mashup.domain.model.chat.Room
import com.mashup.domain.repository.ChatRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatRoomsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : FlowUseCase<Unit, PagingData<Room>>() {
    override fun invoke(params: Unit): Flow<PagingData<Room>> =
        chatRepository.getChatRooms()
}