package com.mashup.data.source.remote.dto.requestbody

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KeywordsRequestBody(
    @field:Json(name = "keywords")
    val keywords: List<String>
)