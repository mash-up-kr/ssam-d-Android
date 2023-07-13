package com.mashup.domain.repository

import com.mashup.domain.model.User
import com.mashup.domain.usecase.LoginParam

interface LoginRepository {
    suspend fun login(param: LoginParam): Boolean
    suspend fun patchNickname(nickname: String)

    suspend fun patchAlarm(isAgree: Boolean)

    suspend fun getUser(id: Long): User

    suspend fun deleteUser(id: Long)
}
