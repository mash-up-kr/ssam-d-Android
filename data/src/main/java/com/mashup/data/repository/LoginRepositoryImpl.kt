package com.mashup.data.repository

import com.mashup.data.source.remote.datasource.RemoteLoginDataSource
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.usecase.LoginParam
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
): LoginRepository {

    override suspend fun login(param: LoginParam): String {
        val loginRequestBody = with(param) {
            LoginRequestBody(
                email = email,
                socialId = socialId,
                deviceToken = deviceToken
            )
        }
        return remoteLoginDataSource.login(loginRequestBody).accessToken
    }
}
