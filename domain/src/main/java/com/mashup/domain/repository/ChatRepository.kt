package com.mashup.domain.repository

interface ChatRepository {

    suspend fun getChatRooms()
}