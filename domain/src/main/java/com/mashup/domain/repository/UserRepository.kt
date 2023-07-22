package com.mashup.domain.repository

import com.mashup.domain.model.User
import com.mashup.domain.usecase.LoginParam

interface UserRepository {
    suspend fun login(param: LoginParam)

    suspend fun logout()

    suspend fun getUserAccessToken(): String

    suspend fun getNicknameDuplication(nickname: String): Result<Unit>

    suspend fun patchNickname(nickname: String): Result<Unit>

    suspend fun patchAlarm(isAgree: Boolean)

    suspend fun getUser(): User

    suspend fun deleteUser()
}
