package com.mashup.domain.repository

import com.mashup.domain.usecase.LoginParam

interface UserRepository {
    suspend fun login(param: LoginParam)
    suspend fun patchNickname(nickname: String)
}
