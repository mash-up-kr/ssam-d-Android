package com.mashup.data.source.remote.datasource

import com.mashup.data.source.remote.dto.BaseResponse
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

    suspend fun getRecommendKeyword(content: String): BaseResponse<KeywordRecommendResponse> {
        return keywordService.getRecommendKeyword(content)
    }
}