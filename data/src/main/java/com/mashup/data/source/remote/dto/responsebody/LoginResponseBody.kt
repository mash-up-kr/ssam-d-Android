package com.mashup.data.source.remote.dto.responsebody

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseBody(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
    val deviceToken: String?
)
