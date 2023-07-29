package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.AlarmRequestBody
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.source.remote.dto.responsebody.LoginResponseBody
import com.mashup.data.source.remote.dto.responsebody.user.SentSignalDetailResponse
import com.mashup.data.source.remote.dto.responsebody.user.SentSignalPagingResponse
import com.mashup.data.source.remote.dto.responsebody.user.SentSignalResponse
import com.mashup.data.source.remote.dto.responsebody.user.UserResponseBody
import com.mashup.data.source.remote.service.UserService
import com.mashup.domain.model.signal.SentSignal
import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(
    private val userService: UserService
) {

    suspend fun login(loginRequestBody: LoginRequestBody): LoginResponseBody {
        val response = userService.login(loginRequestBody)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getNicknameDuplication(nickname: String) {
        userService.getNicknameDuplication(nickname)
    }

    suspend fun patchNickname(nickname: String) {
        userService.patchNickname(nickname)
    }

    suspend fun patchAlarm(isAgree: Boolean) {
        userService.patchAlarm(AlarmRequestBody(isAgree))
    }

    suspend fun getUser(id: Long): BaseResponse<UserResponseBody> {
        return userService.getUser(id)
    }

    suspend fun deleteUser(id: Long) {
        userService.deleteUser(id)
    }

    suspend fun getSentSignals(pageNumber: Int, pageLength: Int?): SentSignalPagingResponse {
        val response =
            userService.getSentSignal(pageNumber = pageNumber, pageLength = pageLength)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getSentSignalDetail(signalId: Long): SentSignalDetailResponse {
        val response = userService.getSentSignalDetail(signalId)
        return response.data ?: throw Exception(response.message)
    }
}
