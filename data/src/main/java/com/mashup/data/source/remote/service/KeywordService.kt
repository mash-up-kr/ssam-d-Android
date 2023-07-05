package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.responsebody.keyword.KeywordRecommendResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
interface KeywordService {
    @GET(ApiPattern.Keywords.recommend)
    suspend fun getRecommendKeyword(
        @Query("content") content: String
    ): BaseResponse<KeywordRecommendResponse>

}