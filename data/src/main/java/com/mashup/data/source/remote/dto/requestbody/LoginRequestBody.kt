package com.mashup.data.source.remote.dto.requestbody

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestBody(
    val email: String?,
    val socialId: String,
    val provider: String = "KAKAO",
    val deviceToken: String?
)
