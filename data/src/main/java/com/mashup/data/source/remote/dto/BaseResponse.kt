package com.mashup.data.source.remote.dto

import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = "data")
    val data: T,
    @Json(name = "message")
    val message: String,
)