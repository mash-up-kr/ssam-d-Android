package com.mashup.domain.repository

import com.mashup.domain.usecase.LoginParam

interface LoginRepository {
    suspend fun login(param: LoginParam): Boolean
    suspend fun getNicknameDuplication(nickname: String)
    suspend fun patchNickname(nickname: String)
}
