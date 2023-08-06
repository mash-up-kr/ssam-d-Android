package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.requestbody.ReplyRequestBody
import com.mashup.data.source.remote.dto.responsebody.crash.CrashPagingResponse
import com.mashup.data.source.remote.dto.responsebody.crash.CrashResponse
import com.mashup.data.source.remote.service.CrashService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCrashDataSource @Inject constructor(
    private val crashService: CrashService
) {
    suspend fun getCrashes(pageNumber: Int, pageLength: Int?): CrashPagingResponse {
        val response = crashService.getCrashes(pageNumber = pageNumber, pageLength = pageLength)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun getCrashDetail(crashId: Long): CrashResponse {
        val response = crashService.getCrashDetail(crashId)
        return response.data ?: throw Exception(response.message)
    }

    suspend fun replyCrash(crashId: Long, content: String) {
        val replyRequest = ReplyRequestBody(
            content = content
        )
        crashService.replyCrash(crashId = crashId, replyRequest = replyRequest)
    }
}