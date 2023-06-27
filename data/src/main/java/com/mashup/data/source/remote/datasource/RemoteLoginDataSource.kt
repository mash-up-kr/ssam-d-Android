package com.mashup.data.source.remote.datasource

import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.source.remote.dto.responsebody.LoginResponseBody
import com.mashup.data.source.remote.service.LoginService
import javax.inject.Inject

class RemoteLoginDataSource @Inject constructor(
    private val loginService: LoginService
) {

    suspend fun login(loginRequestBody: LoginRequestBody): LoginResponseBody {
        return loginService.login(loginRequestBody).data ?: throw NullPointerException()
    }
}
