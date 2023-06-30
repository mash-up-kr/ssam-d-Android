package com.mashup.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "data")
    val data: T?,
    @Json(name = "message")
    val message: String,
    @Json(name = "statusCode")
    val statusCode: Int?
)