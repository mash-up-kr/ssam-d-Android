package com.mashup.data.repository

import com.mashup.data.source.local.datasource.LocalUserDataSource
import com.mashup.data.source.remote.source.datasource.RemoteUserDataSource
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.util.suspendRunCatching
import com.mashup.domain.model.User
import com.mashup.domain.repository.UserRepository
import com.mashup.domain.usecase.login.LoginParam
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {

    override suspend fun login(param: LoginParam) {
        val loginRequestBody = with(param) {
            LoginRequestBody(
                email = email,
                socialId = socialId,
                deviceToken = deviceToken
            )
        }

        with(remoteUserDataSource.login(loginRequestBody)) {
            localUserDataSource.saveToken(accessToken)
            localUserDataSource.setUserId(userId)
        }
    }

    override suspend fun logout() = suspendRunCatching { localUserDataSource.removeToken() }

    override suspend fun getNicknameDuplication(nickname: String): Result<Unit> {
        return suspendRunCatching {
            remoteUserDataSource.getNicknameDuplication(nickname)
        }
    }

    override suspend fun patchNickname(nickname: String): Result<Unit> {
        return suspendRunCatching {
            remoteUserDataSource.patchNickname(nickname)
        }.onSuccess {
            localUserDataSource.setNickname(nickname)
        }.onFailure { throw Exception(it.message) }
    }

    override suspend fun patchAlarm(isAgree: Boolean) {
        remoteUserDataSource.patchAlarm(isAgree)
    }

    override suspend fun getUser(): User {
        val id = localUserDataSource.getUserId()
        remoteUserDataSource.getUser(id).let { result ->
            return result.data?.toDomainModel() ?: throw Exception(result.message)
        }
    }

    override suspend fun deleteUser(userId: Long): Result<Unit> {
        return suspendRunCatching {
            remoteUserDataSource.deleteUser(userId)
        }.onSuccess {
            localUserDataSource.removeAll()
        }.onFailure {
            throw Exception(it.message)
        }
    }

    override suspend fun getUserAccessToken(): String = localUserDataSource.getToken()

    override suspend fun getNickname(): String = localUserDataSource.getNickname()

    override suspend fun getKeywords(): List<String> = localUserDataSource.getKeywords()
}
