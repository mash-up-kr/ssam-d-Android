package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.SignalRequest
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignalDetail
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignalResponse
import retrofit2.http.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
interface SignalService {

    @POST(ApiPattern.Signal.SEND_SIGNAL)
    suspend fun sendSignal(
        @Body signalRequest: SignalRequest
    ): BaseResponse<Any>

    @GET(ApiPattern.Signal.PREFIX)
    suspend fun getReceivedSignal(
        @Query("pageNo") pageNumber: Int,
        @Query("pageLength") pageLength: Int?
    ): BaseResponse<ReceivedSignalResponse>

    @GET("${ApiPattern.Signal.PREFIX}/{id}")
    suspend fun getReceivedSignalDetail(
        @Path("id") signalId: Long
    ): BaseResponse<ReceivedSignalDetail>
}