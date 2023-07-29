package com.mashup.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @field:Json(name = "data")
    val data: T?,
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "statusCode")
    val statusCode: Int?
)