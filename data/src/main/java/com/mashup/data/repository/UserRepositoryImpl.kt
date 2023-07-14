package com.mashup.data.repository

import com.mashup.data.source.local.datasource.LocalUserDataSource
import com.mashup.data.source.remote.datasource.RemoteUserDataSource
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.util.suspendRunCatching
import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.LoginParam
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
): UserRepository {

    override suspend fun login(param: LoginParam): Boolean {
        val loginRequestBody = with(param) {
            LoginRequestBody(
                email = email,
                socialId = socialId,
                deviceToken = deviceToken
            )
        }

        val token = runCatching {
            remoteUserDataSource.login(loginRequestBody).data
        }.getOrNull()?.accessToken ?: ""

        localUserDataSource.saveToken(token)

        return token.isNotEmpty()
    }

    override suspend fun getNicknameDuplication(nickname: String): Result<Unit> {
        return suspendRunCatching {
            remoteUserDataSource.getNicknameDuplication(nickname)
        }
    }

    override suspend fun patchNickname(nickname: String) {
        remoteUserDataSource.patchNickname(nickname)
    }
}
