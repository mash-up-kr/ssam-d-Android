package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.source.remote.dto.responsebody.LoginResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.POST

interface LoginService {

    @POST("auth/login")
    suspend fun login(
        @Body requestBody: LoginRequestBody
    ): BaseResponse<LoginResponseBody>

    @FormUrlEncoded
    @PATCH("users/nickname")
    suspend fun patchNickname(
        @Field("nickname") nickname: String
    ): BaseResponse<Any>
}