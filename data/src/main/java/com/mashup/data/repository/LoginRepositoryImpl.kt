package com.mashup.data.repository

import com.mashup.data.source.local.datasource.LocalLoginDataSource
import com.mashup.data.source.remote.datasource.RemoteLoginDataSource
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.usecase.ConflictException
import com.mashup.domain.usecase.LoginParam
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val localLoginDataSource: LocalLoginDataSource
): LoginRepository {

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

    override suspend fun getNicknameDuplication(nickname: String) {
        try {
            remoteLoginDataSource.getNicknameDuplication(nickname)
        } catch (e: HttpException) {
            when (e.code()) {
                409 -> throw ConflictException("ConflictException")
                else -> throw Exception("Exception")
            }
        }
    }

    override suspend fun patchNickname(nickname: String) {
        remoteLoginDataSource.patchNickname(nickname)
    }
}
