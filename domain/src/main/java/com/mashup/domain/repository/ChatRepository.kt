package com.mashup.domain.repository

import com.mashup.domain.model.ChatDetail

interface ChatRepository {

    suspend fun getChatRooms(id: Long): ChatDetail
}