package com.mashup.domain.repository

import com.mashup.domain.model.User
import com.mashup.domain.usecase.login.LoginParam

interface UserRepository {
    suspend fun login(param: LoginParam)

    suspend fun logout(): Result<Unit>

    suspend fun getNicknameDuplication(nickname: String): Result<Unit>

    suspend fun patchNickname(nickname: String): Result<Unit>

    suspend fun patchAlarm(isAgree: Boolean)

    suspend fun getUser(): User

    suspend fun deleteUser(userId: Long): Result<Unit>

    suspend fun getUserAccessToken(): String

    suspend fun getNickname(): String

    suspend fun getKeywords(): List<String>
}
