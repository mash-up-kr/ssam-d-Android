package com.mashup.data.source.remote.dto.responsebody.keyword

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
@JsonClass(generateAdapter = true)
data class KeywordRecommendResponse(
    @field:Json(name = "keywords")
    val keywords: List<String>
)
