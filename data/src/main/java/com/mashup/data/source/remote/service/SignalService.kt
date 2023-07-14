package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.SignalRequest
import retrofit2.http.Body
import retrofit2.http.POST

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
}