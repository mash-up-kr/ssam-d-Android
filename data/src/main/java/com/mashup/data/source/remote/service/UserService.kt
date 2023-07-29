package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.AlarmRequestBody
import com.mashup.data.source.remote.dto.requestbody.LoginRequestBody
import com.mashup.data.source.remote.dto.responsebody.LoginResponseBody
import com.mashup.data.source.remote.dto.responsebody.UserResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path

interface UserService {

    @POST("auth/login")
    suspend fun login(
        @Body requestBody: LoginRequestBody
    ): BaseResponse<LoginResponseBody>

    @GET("users/nickname/duplication")
    suspend fun getNicknameDuplication(
        @Query("nickname") nickname: String
    ): BaseResponse<Any>

    @FormUrlEncoded
    @PATCH("users/nickname")
    suspend fun patchNickname(
        @Field("nickname") nickname: String
    ): BaseResponse<Any>

    @PATCH("users/alarm")
    suspend fun patchAlarm(
        @Body agreeAlarm: AlarmRequestBody
    ): BaseResponse<Any>

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Long
    ): BaseResponse<UserResponseBody>

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Long
    ): BaseResponse<Any>
}