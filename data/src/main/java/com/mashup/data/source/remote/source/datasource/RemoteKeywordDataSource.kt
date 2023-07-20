package com.mashup.data.source.remote.source.datasource

import com.mashup.data.source.remote.dto.requestbody.KeywordsRequestBody
import com.mashup.data.source.remote.dto.responsebody.keyword.KeywordRecommendResponse
import com.mashup.data.source.remote.service.KeywordService
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
class RemoteKeywordDataSource @Inject constructor(
    private val keywordService: KeywordService
) {
    suspend fun getSubscribeKeywords(): List<String> {
        val response = keywordService.getSubscribeKeywords()
        return response.data ?: throw Exception(response.message)
    }

    suspend fun postSubscribeKeywords(subscribeKeywords: List<String>): String {
        val response = keywordService.postSubscribeKeywords(
            keywordsRequest = KeywordsRequestBody(
                keywords = subscribeKeywords
            )
        )
        return response.message
    }

    suspend fun getRecommendKeyword(content: String): KeywordRecommendResponse {
        val response = keywordService.getRecommendKeyword(content)
        return response.data ?: throw Exception(response.message)
    }
}