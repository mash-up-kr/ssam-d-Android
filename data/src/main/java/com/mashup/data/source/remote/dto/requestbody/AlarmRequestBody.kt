package com.mashup.data.source.remote.dto.requestbody

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlarmRequestBody(
    val agreeAlarm: Boolean
)