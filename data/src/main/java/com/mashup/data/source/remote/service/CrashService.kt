package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.ReplyRequestBody
import com.mashup.data.source.remote.dto.responsebody.crash.CrashPagingResponse
import com.mashup.data.source.remote.dto.responsebody.crash.CrashResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CrashService {
    @GET(ApiPattern.Crash.PREFIX)
    suspend fun getCrashes(
        @Query("pageNo") pageNumber: Int,
        @Query("pageLength") pageLength: Int?
    ): BaseResponse<CrashPagingResponse>

    @GET("${ApiPattern.Crash.PREFIX}/{id}")
    suspend fun getCrashDetail(
        @Path("id") crashId: Long
    ): BaseResponse<CrashResponse>

    @POST("${ApiPattern.Crash.PREFIX}/{id}${ApiPattern.Crash.REPLY_CRASH}")
    suspend fun replyCrash(
        @Path("id") crashId: Long,
        @Body replyRequest: ReplyRequestBody
    ): BaseResponse<Any>
}