package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.User
import com.mashup.domain.model.signal.SentSignal
import com.mashup.domain.model.signal.SentSignalDetail
import com.mashup.domain.usecase.login.LoginParam
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(param: LoginParam)

    suspend fun logout(): Result<Unit>

    suspend fun getNicknameDuplication(nickname: String): Result<Unit>

    suspend fun patchNickname(nickname: String): Result<Unit>

    suspend fun patchAlarm(isAgree: Boolean)

    suspend fun getUser(): User

    suspend fun deleteUser()

    suspend fun getUserAccessToken(): String

    suspend fun getNickname(): String

    suspend fun getKeywords(): List<String>

    fun getSentSignals(): Flow<PagingData<SentSignal>>

    fun getSendSignalDetail(signalId: Long): Flow<SentSignalDetail>
}
