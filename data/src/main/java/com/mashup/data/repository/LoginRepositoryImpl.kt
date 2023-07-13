package com.mashup.data.repository

import com.mashup.data.source.local.datasource.LocalLoginDataSource
import com.mashup.data.source.remote.datasource.RemoteLoginDataSource
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.domain.model.User
import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.usecase.LoginParam
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val localLoginDataSource: LocalLoginDataSource
) : LoginRepository {

    override suspend fun login(param: LoginParam): Boolean {
        val loginRequestBody = with(param) {
            LoginRequestBody(
                email = email,
                socialId = socialId,
                deviceToken = deviceToken
            )
        }

        val token = runCatching {
            remoteLoginDataSource.login(loginRequestBody).data
        }.getOrNull()?.accessToken ?: ""

        localLoginDataSource.saveToken(token)

        return token.isNotEmpty()
    }

    override suspend fun patchNickname(nickname: String) {
        remoteLoginDataSource.patchNickname(nickname)
    }

    override suspend fun patchAlarm(isAgree: Boolean) {
        remoteLoginDataSource.patchAlarm(isAgree)
    }

    override suspend fun getUser(id: Long): User {
        remoteLoginDataSource.getUser(id).let {result ->
            return result.data?.toDomainModel() ?: throw Exception(result.message)
        }
    }

    override suspend fun deleteUser(id: Long) {
        remoteLoginDataSource.deleteUser(id)
    }
}
