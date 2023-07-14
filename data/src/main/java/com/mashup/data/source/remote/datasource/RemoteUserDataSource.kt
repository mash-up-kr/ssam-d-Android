package com.mashup.data.source.remote.datasource

import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.source.remote.dto.responsebody.LoginResponseBody
import com.mashup.data.source.remote.service.UserService
import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(
    private val userService: UserService
) {

    suspend fun login(loginRequestBody: LoginRequestBody): LoginResponseBody {
        val response = userService.login(loginRequestBody)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun patchNickname(nickname: String) {
        userService.patchNickname(nickname)
    }
}
