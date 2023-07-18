package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.SignalRequest
import com.mashup.data.source.remote.dto.responsebody.signal.ReceivedSignalResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        @Query("pageLength") pageLength: Int = PAGE_LENGTH
    ): BaseResponse<ReceivedSignalResponse>

    companion object {
        const val PAGE_LENGTH = 10
    }
}