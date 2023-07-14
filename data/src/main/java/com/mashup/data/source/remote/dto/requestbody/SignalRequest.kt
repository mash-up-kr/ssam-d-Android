package com.mashup.data.source.remote.dto.requestbody

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
@JsonClass(generateAdapter = true)
data class SignalRequest(
    @field:Json(name = "content")
    val content: String,
    @field:Json(name = "keywords")
    val keywords: List<String>
)