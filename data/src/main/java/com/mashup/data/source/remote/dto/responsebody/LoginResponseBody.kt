package com.mashup.data.source.remote.dto.responsebody

data class LoginResponseBody(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
    val deviceToken: String?
)
