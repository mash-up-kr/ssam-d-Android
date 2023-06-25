package com.mashup.data.source.remote.dto.requestbody

data class LoginRequestBody(
    val email: String?,
    val socialId: String,
    val provider: String = "KAKAO",
    val deviceToken: String?
)
