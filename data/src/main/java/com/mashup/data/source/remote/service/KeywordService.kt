package com.mashup.data.source.remote.service

import com.mashup.data.source.remote.dto.BaseResponse
import com.mashup.data.source.remote.dto.requestbody.KeywordsRequestBody
import com.mashup.data.source.remote.dto.responsebody.keyword.KeywordRecommendResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
interface KeywordService {

    @GET(ApiPattern.Keywords.PREFIX)
    suspend fun getSubscribeKeywords(): BaseResponse<List<String>>

    @POST(ApiPattern.Keywords.PREFIX)
    suspend fun postSubscribeKeywords(
        @Body keywordsRequest: KeywordsRequestBody
    ): BaseResponse<Any>

    @GET(ApiPattern.Keywords.RECOMMEND)
    suspend fun getRecommendKeyword(
        @Query("content") content: String
    ): BaseResponse<KeywordRecommendResponse>

}